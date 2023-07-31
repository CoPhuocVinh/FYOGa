package org.jio.fyoga.service.impl;/*  Welcome to Jio word
    @author: Jio
    Date: 7/21/2023
    Time: 2:58 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Slot;
import org.jio.fyoga.repository.SlotRepository;
import org.jio.fyoga.service.ISlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotServiceImpl implements ISlotService {
    @Autowired
    SlotRepository slotRepository;

    public SlotServiceImpl(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @Override
    public Slot findById(Integer integer) {
        return slotRepository.findById(integer).orElseThrow();
    }

    @Override
    public <S extends Slot> S save(S entity) {
        return slotRepository.save(entity);
    }

    @Override
    public List<Slot> findAll() {
        return slotRepository.findAll();
    }
}
