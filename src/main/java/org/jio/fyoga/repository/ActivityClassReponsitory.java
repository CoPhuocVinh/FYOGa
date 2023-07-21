package org.jio.fyoga.repository;/*  Welcome to Jio word
    @author: Jio
    Date: 7/21/2023
    Time: 1:56 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Account;
import org.jio.fyoga.entity.ActivityClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityClassReponsitory extends JpaRepository<ActivityClass, Integer> {
    @Query("SELECT ac.dayOfWeek, ac.activityID FROM ActivityClass ac WHERE ac.slot.slotID = ?1 AND ac.schedule.scheduleID = ?2")
    List<Object[]> findActivityClassesFromMondayToSaturday(int slotId , int scheduleID);
}
