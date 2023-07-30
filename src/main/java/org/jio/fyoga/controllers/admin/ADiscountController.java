package org.jio.fyoga.controllers.admin;

import org.jio.fyoga.entity.Discount;
import org.jio.fyoga.model.DiscountDTO;
import org.jio.fyoga.service.IDiscountService;
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

    @GetMapping("/CreateOrUpdateDiscount")
    public String showCreateOrUpdate(@RequestParam int isEdit,
                                     @RequestParam(name = "DiscountID", required = false, defaultValue = "-1") String R_DiscountID,
                                     Model model) {
        int discountID = Integer.parseInt(R_DiscountID);

        // Xử lý chỉnh sửa
        DiscountDTO discountDTO = DiscountDTO.builder().build();
        if (isEdit == 1 && discountID >= 0) {
            Optional<Discount> discountEntityOptional = discountService.findById(discountID);
            if (discountEntityOptional.isPresent()) {
                Discount discountEntity = discountEntityOptional.get();
                BeanUtils.copyProperties(discountEntity, discountDTO);
                discountDTO.setIsEdit(true);
            }
        }

        // Xử lý tạo mới
        else if (isEdit == 0) {
            discountDTO.setIsEdit(false);
        }

        model.addAttribute("DISCOUNTDTO", discountDTO);
        return "admin/add&editDiscount";
    }

    @PostMapping("/CreateOrUpdateDiscount")
    public String createOrUpdate(@ModelAttribute("DISCOUNTDTO") DiscountDTO discountDTO,
                                 RedirectAttributes ra) {
        if (discountDTO.getIsEdit()) {
            // Xử lý chỉnh sửa
            Optional<Discount> discountEntityOptional = discountService.findById(discountDTO.getDiscountID());
            if (discountEntityOptional.isPresent()) {
                Discount discountEntity = discountEntityOptional.get();
                BeanUtils.copyProperties(discountDTO, discountEntity);
                discountService.save(discountEntity);
            }
        } else {
            // Xử lý tạo mới
            Discount discountEntity = new Discount();
            BeanUtils.copyProperties(discountDTO, discountEntity);
            discountService.save(discountEntity);
        }

        ra.addFlashAttribute("MSG", "Save successfully!!!");
        return "redirect:/FYoGa/Login/ADMIN/Discount";
    }

    @GetMapping("/removeDiscount")
    public String removeDiscount(@RequestParam int discountID) {
        discountService.deleteById(discountID);
        return "redirect:/FYoGa/Login/ADMIN/Discount";
    }
}
