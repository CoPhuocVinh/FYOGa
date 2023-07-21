package org.jio.fyoga.repository;/*  Welcome to Jio word
    @author: Jio
    Date: 7/21/2023
    Time: 2:57 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Package;
import org.jio.fyoga.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {
}
