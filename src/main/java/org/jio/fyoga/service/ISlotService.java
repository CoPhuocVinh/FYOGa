package org.jio.fyoga.service;/*  Welcome to Jio word
    @author: Jio
    Date: 7/21/2023
    Time: 2:58 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Slot;

import java.util.List;

public interface ISlotService {
    Slot findById(Integer integer);

    <S extends Slot> S save(S entity);
    List<Slot> findAll();
}
