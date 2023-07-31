package org.jio.fyoga.controllers.admin;

import org.jio.fyoga.entity.Discount;
import org.jio.fyoga.entity.Package;
import org.jio.fyoga.model.DiscountDTO;
import org.jio.fyoga.model.PackageDTO;
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

@RequestMapping("/FYoGa/Login/ADMIN/Discount")
@Controller
public class ADiscountController {

    @Autowired
    private IDiscountService discountService;
    @Autowired
    IPackageService packageService;

    @GetMapping("/{packageID}/Discount")
    public String getDiscountsForPackage(@PathVariable int packageID, Model model) {
        List<Discount> discounts = discountService.findAllByAPackage_PackageID(packageID);
        model.addAttribute("DISCOUNTS", discounts);
        model.addAttribute("PACKAGEID", packageID);
        return "admin/page_discount_detail";
    }

    @GetMapping("/CreateOrUpdateDiscount")
    public String showCreateOrUpdate(@RequestParam int isEdit,
                                     @RequestParam(name = "packageID", required = false, defaultValue = "-1")String R_packageID,
                                     @RequestParam(name = "DiscountID", required = false, defaultValue = "-1") String R_DiscountID,
                                     Model model) {
        int discountID = Integer.parseInt(R_DiscountID);
        int packageID = Integer.parseInt(R_packageID);

        // Xử lý chỉnh sửa
        DiscountDTO discountDTO = DiscountDTO.builder().build();
        Package packageEntity = packageService.findById(packageID).orElse(null);
        if (isEdit == 1 && discountID >= 0) {
            Optional<Discount> discountEntityOptional = discountService.findById(discountID);
            if (discountEntityOptional.isPresent()) {

                Discount discountEntity = discountEntityOptional.get();
                BeanUtils.copyProperties(discountEntity, discountDTO);

                discountDTO.setPackageID(discountEntity.getAPackage().getPackageID());
                discountDTO.setIsEdit(true);
            }
        }

        // Xử lý tạo mới
        else if (isEdit == 0) {

            discountDTO.setPackageID(packageID);

            discountDTO.setIsEdit(false);
        }

        model.addAttribute("DISCOUNTDTO", discountDTO);
        model.addAttribute("P", packageEntity);

        return "admin/add&editDiscount";
    }

    @PostMapping("/CreateOrUpdateDiscount")
    public String createOrUpdate(@ModelAttribute("DISCOUNTDTO") DiscountDTO discountDTO,
                                 RedirectAttributes ra) {
        Package packageEntity = packageService.findById(discountDTO.getPackageID()).orElse(null);


        if (discountDTO.getIsEdit()) {
            // Xử lý chỉnh sửa
            Optional<Discount> discountEntityOptional = discountService.findById(discountDTO.getDiscountID());
            if (discountEntityOptional.isPresent()) {
                Discount discountEntity = discountEntityOptional.get();
                BeanUtils.copyProperties(discountDTO, discountEntity);

                discountEntity.setAPackage(packageEntity);
                discountEntity.setTimeOnMonth(discountDTO.getTimeOnMonth());
                discountEntity.setPercentDiscount(discountEntity.getPercentDiscount());

                discountService.save(discountEntity);
            }
        } else {
            // Xử lý tạo mới
            Discount discountEntity = new Discount();
            BeanUtils.copyProperties(discountDTO, discountEntity);


            discountEntity.setAPackage(packageEntity);


            discountService.save(discountEntity);
        }

        ra.addFlashAttribute("MSG", "Save successfully!!!");

        return "redirect:/FYoGa/Login/ADMIN/Discount/"+discountDTO.getPackageID()+"/Discount";
    }

    @GetMapping("/removeDiscount")
    public String removeDiscount(@RequestParam int discountID, @RequestParam int packageID) {
        discountService.deleteById(discountID);
        return "redirect:/FYoGa/Login/ADMIN/Discount/" + packageID + "/Discount";
    }


}
