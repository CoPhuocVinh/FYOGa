package org.jio.fyoga.controllers.web;/*  Welcome to Jio word
    @author: Jio
    Date: 6/30/2023
    Time: 12:45 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import jakarta.servlet.http.HttpSession;
import org.jio.fyoga.config.Config;
import org.jio.fyoga.entity.Account;
import org.jio.fyoga.entity.Discount;
import org.jio.fyoga.entity.Package;
import org.jio.fyoga.entity.Register;
import org.jio.fyoga.model.RegisterDTO;
import org.jio.fyoga.service.IDiscountService;
import org.jio.fyoga.service.IPackageService;
import org.jio.fyoga.service.IRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/FYoGa/Course/PackageCheckOut")
@Controller
public class CheckOutController {

    @Autowired
    IPackageService packageService;
    @Autowired
    IRegisterService registerService;

    @Autowired
    IDiscountService discountService;

    @GetMapping("")
    public String ShowCheckOut(HttpSession session
            , @RequestParam int discountID, Model model
            , @RequestParam int typePaying){

        String url = "web/checkoutCourse";
        Account account = (Account) session.getAttribute("USER");
        RegisterDTO registerDTO = RegisterDTO.builder().build();
        if(account == null){
            //System.out.println(packageID);
            session.setAttribute("CHECKOUTING", discountID);
            url = "redirect:/FYoGa/Login";
        }
        Optional<Discount> discountEntity = discountService.findById(discountID);
        if(typePaying == 0 ){
            registerDTO.setTypePaying(0);
            model.addAttribute("TYPEPAYING", "Thanh toán tại quầy");


        }else {
            registerDTO.setTypePaying(1);
            model.addAttribute("TYPEPAYING", "Thanh toán VNPAY");
        }

        model.addAttribute("PAYING",discountEntity);
        model.addAttribute("REGISTER",registerDTO);

        // xu ly register thanh cong
        String SUCCESS = (String) session.getAttribute("SUCCESS");
        if (SUCCESS != null){
            model.addAttribute("SUCCESS",SUCCESS);
            session.removeAttribute("SUCCESS");
        }
        return url;
    }

    @PostMapping("/Checkout")
    public String Checkout(HttpSession session, @RequestParam int discountID
            ,@ModelAttribute("REGISTER") RegisterDTO registerDTO, Model model){

        Account account = (Account) session.getAttribute("USER");
        //Optional<Package> packageEntiry = packageService.findById(packageID);
        Optional<Discount> discountEntity = discountService.findById(discountID);

        float price_discount = discountEntity.get().getAPackage().getPrice() * (100 - discountEntity.get().getPercentDiscount())/100;
        int slotAvailable = discountEntity.get().getAPackage().getSlotOnMonth()*discountEntity.get().getTimeOnMonth();
        int timeAvailable = discountEntity.get().getTimeOnMonth();

        if (registerDTO.getTypePaying() == 0){
            Date date = new Date(System.currentTimeMillis());
             registerDTO = RegisterDTO.builder()
                    .customerID(account.getAccountID())
                    .aDiscountID(discountID)
                    .status(2)
                    .priceOriginal(discountEntity.get().getAPackage().getPrice())
                    .priceDiscount(price_discount)
                    .slotAvailable(slotAvailable)
                    .timeAvailable(timeAvailable)
                    .slotUsed(slotAvailable)
                    .registeredDate(date)
                    .weekUsed(timeAvailable*4)
                    .build();

            Register registerEntity = new Register();
            BeanUtils.copyProperties(registerDTO,registerEntity);
            registerEntity.setCustomer(account);
            //registerEntity.setPackages(packageEntiry.orElseThrow());
            registerEntity.setADiscount(discountEntity.orElseThrow());

            registerService.save(registerEntity);
        }else {
            registerDTO = RegisterDTO.builder()
                    .aDiscountID(discountID)
                    .priceOriginal(discountEntity.get().getAPackage().getPrice())
                    .priceDiscount(price_discount)
                    .slotAvailable(slotAvailable)
                    .timeAvailable(timeAvailable)
                    .slotUsed(slotAvailable)
                    .weekUsed(timeAvailable*4)
                    .typePaying(1)
                    .build();
            session.setAttribute("REGISTER",registerDTO);
            return "redirect:/FYoGa/Course/PackageCheckOut/create_payment?discountID=" + discountID;

        }

        System.out.println("register thành công");
        session.setAttribute("SUCCESS","Bạn đã đăng kí khóa học thành công");
        return "redirect:/FYoGa/Course/PackageCheckOut?discountID=" + discountID+"&typePaying=0";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // /FYoGa/Course/PackageCheckOut/create_payment
    @GetMapping("/create_payment")
    public String payment(HttpSession session
            , @RequestParam int discountID, Model model) throws UnsupportedEncodingException {

        Optional<Discount> discountEntity = discountService.findById(discountID);

        //ResponseEntity<?>
        String orderType = "billpayment";
//        long amount = Integer.parseInt(req.getParameter("amount"))*100;
//        String bankCode = req.getParameter("bankCode");

        float price_discount = discountEntity.get().getAPackage().getPrice() * (100 - discountEntity.get().getPercentDiscount());

        long amount = (long) price_discount;

        String vnp_TxnRef = Config.getRandomNumber(8);
        //String vnp_IpAddr = Config.getIpAddress(req);
        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", Config.vnp_Version);
        vnp_Params.put("vnp_Command", Config.vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_Locale", "vn");
//        if (bankCode != null && !bankCode.isEmpty()) {
//            vnp_Params.put("vnp_BankCode", bankCode);
//        }

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        //String locate = req.getParameter("language");
//        if (locate != null && !locate.isEmpty()) {
//            vnp_Params.put("vnp_Locale", locate);
//        } else {
//            vnp_Params.put("vnp_Locale", "vn");
//        }

        vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", Config.vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        System.out.println(paymentUrl);

//
//        PaymentDTO paymentDTO = PaymentDTO.builder()
//                .status("Ok")
//                .message("success")
//                .URL(paymentUrl)
//                .build();

        return "redirect:" + paymentUrl;
        //return ResponseEntity.status(HttpStatus.OK).body(paymentDTO);
    }

    // /FYoGa/Course/PackageCheckOut/payment
    @Transactional
    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String processPayment(@RequestParam("vnp_Amount") String amount,
                                 @RequestParam("vnp_BankCode") String bankCode,
                                 @RequestParam("vnp_ResponseCode") String responseCode,
                                 @RequestParam("vnp_OrderInfo") String order, Model model,
                                 HttpSession session) {
        RegisterDTO registerDTO = (RegisterDTO) session.getAttribute("REGISTER");
        Account account = (Account) session.getAttribute("USER");
        Optional<Discount> discountEntity = discountService.findById(registerDTO.getADiscountID());

        if (responseCode.equals("00")) {
            Date date = new Date(System.currentTimeMillis());
            registerDTO.setRegisteredDate(date);
            Register registerEntity = new Register();


            BeanUtils.copyProperties(registerDTO,registerEntity);
            registerEntity.setCustomer(account);
            registerEntity.setADiscount(discountEntity.orElseThrow());
            registerService.save(registerEntity);
            System.out.println("register thành công");
            session.setAttribute("SUCCESS","Bạn đã đăng kí và thanh toán khóa học thành công");
            return "redirect:/FYoGa/Course/PackageCheckOut?discountID=" + registerDTO.getADiscountID()+"&typePaying=1";

        }else {
            model.addAttribute("SUCCESS","Bạn đã đăng kí và thanh toán khóa học thất bại");
            session.removeAttribute("SUCCESS");

        }




        return  "";
    }




    }
