package org.jio.fyoga.controllers.admin;

import jakarta.servlet.http.HttpSession;
import org.jio.fyoga.entity.Discount;
import org.jio.fyoga.entity.Package;
import org.jio.fyoga.model.DiscountDTO;
import org.jio.fyoga.service.IDiscountService;
import org.jio.fyoga.service.IPackageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/FYoGa/Login/ADMIN/Discount")
public class ADiscountController {

    @Autowired
    private IDiscountService discountService;

    @Autowired
    private IPackageService packageService;

    @GetMapping("/{packageID}/Discount")
    public String getDiscountsForPackage(@PathVariable int packageID, Model model) {
        List<Discount> discounts = discountService.findAllByAPackage_PackageID(packageID);
        model.addAttribute("DISCOUNTS", discounts);
        return "admin/page_discount_detail";
    }

    @GetMapping("/{packageID}/Discount/{discountID}")
    public String getDiscountDetails(@PathVariable int packageID, @PathVariable int discountID, Model model) {
        Optional<Discount> discount = discountService.findById(discountID);
        if (discount.isPresent()) {
            model.addAttribute("DISCOUNT", discount.get());
            return "admin/page_discount_detail";
        }
        return "redirect:/FYoGa/Login/ADMIN/Discount";
    }

    @GetMapping("/CreateOrEditDiscount")
    public String showCreateorEdit(@RequestParam int isEdit,
                                   @RequestParam(name = "DiscountID", required = false, defaultValue = "-1") String R_DiscountID,
                                   @RequestParam int packageID,
                                   Model model) {
        int DiscountID = Integer.parseInt(R_DiscountID);
        List<Package> packages = packageService.findAll();
        DiscountDTO discountDTO = DiscountDTO.builder().build();

        // Edit
        if (isEdit == 1 && DiscountID >= 0) {
            Discount discountEntity = discountService.findById(DiscountID).orElseThrow();
            BeanUtils.copyProperties(discountEntity, discountDTO);
            discountDTO.setIsEdit(true);
            discountDTO.setPercentDiscount(discountDTO.getPercentDiscount());
            discountDTO.setTimeOnMonth(discountDTO.getTimeOnMonth());
        }
        // Create
        if (isEdit == 0) {
            discountDTO.setIsEdit(false);
        }

        model.addAttribute("DISCOUNTDTO", discountDTO);
        model.addAttribute("PACKAGES", packages);
        return "admin/add&editDiscount";
    }


    @PostMapping("/CreateOrEditDiscount")
    public String createUpdate(HttpSession session,
                               @ModelAttribute("DISCOUNTDTO") DiscountDTO discountDTO,
                               RedirectAttributes ra) {

        Discount discountEntity = new Discount();

        if (discountDTO.getIsEdit()) {
            Optional<Discount> discountOptional = discountService.findById(discountDTO.getDiscountID());
            if (discountOptional.isPresent()) {
                discountEntity = discountOptional.get();
            } else {
                return "admin/add&editDiscount";
            }
        } else {
            // Create new discount
            BeanUtils.copyProperties(discountDTO, discountEntity);
            Optional<Package> packageOptional = packageService.findById(discountDTO.getPackageID());
            if (packageOptional.isPresent()) {
                Package aPackage = packageOptional.get();
                discountEntity.setAPackage(aPackage);
            } else {
                return "admin/add&editDiscount";
            }
        }

        discountEntity.setPercentDiscount(discountDTO.getPercentDiscount());
        discountEntity.setTimeOnMonth(discountDTO.getTimeOnMonth());

        discountService.save(discountEntity);
        ra.addFlashAttribute("MSG", "Saved");
        return "redirect:/FYoGa/Login/ADMIN/Discount/" + discountDTO.getPackageID();
    }

    @GetMapping("/delete")
    public String removeDiscount(@RequestParam int discountID, @RequestParam int packageID){
        discountService.deleteById(discountID);
        return "redirect:/FYoga/Login/ADMIN/Discount/" + packageID+ "/Discount";
    }

}
