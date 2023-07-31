package org.jio.fyoga.repository;/*  Welcome to Jio word
    @author: Jio
    Date: 7/31/2023
    Time: 3:50 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findAllByActivityClass_ActivityID(int id);
    List<Attendance> findAllByActivityClass_ActivityIDAndIsPresent(int id, boolean isPresent);
}
