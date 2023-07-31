package org.jio.fyoga.service;/*  Welcome to Jio word
    @author: Jio
    Date: 7/31/2023
    Time: 3:51 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Attendance;

import java.util.List;
import java.util.Optional;

public interface IAttendanceService {
    List<Attendance> findAllByActivityClass_ActivityID(int id);

    List<Attendance> findAllByActivityClass_ActivityIDAndIsPresent(int id, boolean isPresent);

    <S extends Attendance> List<S> saveAll(Iterable<S> entities);

    <S extends Attendance> S save(S entity);

    Optional<Attendance> findById(Integer integer);
}
