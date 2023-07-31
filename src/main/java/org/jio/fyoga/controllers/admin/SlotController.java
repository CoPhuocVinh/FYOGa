package org.jio.fyoga.controllers.admin;

import org.jio.fyoga.entity.Class;
import org.jio.fyoga.entity.Slot;
import org.jio.fyoga.model.SlotDTO;
import org.jio.fyoga.service.ISlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/FYoGa/Login/ADMIN/slot")
@Controller
public class SlotController {
    @Autowired
    private ISlotService slotService;

    @GetMapping("")
    public String getClass(Model model) {


        List<Slot> slotlist = slotService.findAll();
        model.addAttribute("SLOT", slotlist);

        return "admin/page_slots";

    }
    @GetMapping("/edit/{slotID}")
    public String showEditSlotForm(@PathVariable int slotID, Model model) {
        Slot slot = slotService.findById(slotID);
        if (slot != null) {
            SlotDTO slotDTO = convertToSlotDTO(slot);
            model.addAttribute("slotDTO", slotDTO);
            return "admin/updateSlot";
        }
        return "redirect:/FYoGa/Login/ADMIN/slot/list";
    }

    @PostMapping("/update")
    public String updateSlot(@ModelAttribute SlotDTO slotDTO) {
        Slot slot = slotService.findById(slotDTO.getSlotID());
        if (slot != null) {
            updateSlotFromDTO(slot, slotDTO);
            slotService.save(slot);
        }
        return "redirect:/FYoGa/Login/ADMIN/slot/list";
    }

    // Helper method to convert Slot to SlotDTO
    private SlotDTO convertToSlotDTO(Slot slot) {
        return SlotDTO.builder()
                .slotID(slot.getSlotID())
                .slotName(slot.getSlotName())
                .startTime(slot.getStartTime())
                .endTime(slot.getEndTime())
                .build();
    }

    // Helper method to update Slot entity from SlotDTO
    private void updateSlotFromDTO(Slot slot, SlotDTO slotDTO) {
        slot.setSlotName(slotDTO.getSlotName());
        slot.setStartTime(slotDTO.getStartTime());
        slot.setEndTime(slotDTO.getEndTime());
    }

}
