package org.jio.fyoga.controllers.admin;

import jakarta.validation.Valid;
import org.jio.fyoga.entity.Account;
import org.jio.fyoga.entity.Slot;
import org.jio.fyoga.model.SlotDTO;
import org.jio.fyoga.service.IAccountService;
import org.jio.fyoga.service.ISlotService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/FYoGa/Login/ADMIN/slot")
public class SlotController {
    @Autowired
    private ISlotService slotService;
    @Autowired
    private IAccountService accountService;


    public SlotController(ISlotService slotService, IAccountService accountService) {
        this.slotService = slotService;
        this.accountService = accountService;
    }

    // Helper method to convert Slot to SlotDTO
    private SlotDTO convertToDTO(Slot slot) {
        SlotDTO slotDTO = new SlotDTO();
        BeanUtils.copyProperties(slot, slotDTO);
        if (slot.getStaff() != null) {
            slotDTO.setStaff(slot.getStaff().getAccountID());
        }
        return slotDTO;
    }

    // Helper method to convert SlotDTO to Slot entity
    private Slot convertToEntity(SlotDTO slotDTO) {
        Slot slot = new Slot();
        BeanUtils.copyProperties(slotDTO, slot);
        Account staff = accountService.findById(slotDTO.getStaff());
        slot.setStaff(staff);
        return slot;
    }

    @GetMapping("")
    public String getAllSlots(Model model) {
        List<Slot> slotList = slotService.findAll();
        model.addAttribute("SLOT", slotList);
        return "admin/page_slots";
    }

    @GetMapping("/update/{slotID}")
    public String showUpdateSlotForm(@PathVariable int slotID, Model model) {
        Slot slot = slotService.findById(slotID);
        if (slot == null) {
            // Handle the case where the slot with the given ID doesn't exist
            return "redirect:/FYoGa/Login/ADMIN/slot";
        }

        SlotDTO slotDTO = convertToDTO(slot);
        model.addAttribute("slotDTO", slotDTO);
        return "admin/updateSlot";
    }

    @PostMapping("/updateSlot")
    public String updateSlot(@ModelAttribute("slotDTO") @Valid SlotDTO slotDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the update page with error messages
            System.out.println(bindingResult.getAllErrors());
            return "admin/updateSlot";
        }


        Slot slotEntity = convertToEntity(slotDTO);

        // Cập nhật dữ liệu vào cơ sở dữ liệu
        slotService.save(slotEntity);

        // Thêm thông báo cập nhật thành công vào RedirectAttributes
        redirectAttributes.addFlashAttribute("message", "Slot updated successfully!");

        // Redirect về trang danh sách slot (hoặc trang khác tùy theo nhu cầu của bạn)
        return "redirect:/FYoGa/Login/ADMIN/slot";
    }
}