package org.jio.fyoga.service.impl;/*  Welcome to Jio word
    @author: Jio
    Date: 7/31/2023
    Time: 3:51 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Attendance;
import org.jio.fyoga.repository.AttendanceRepository;
import org.jio.fyoga.service.IAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements IAttendanceService {
    @Autowired
    AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public List<Attendance> findAllByActivityClass_ActivityID(int id) {
        return attendanceRepository.findAllByActivityClass_ActivityID(id);
    }

    @Override
    public List<Attendance> findAllByActivityClass_ActivityIDAndIsPresent(int id, boolean isPresent) {
        return attendanceRepository.findAllByActivityClass_ActivityIDAndIsPresent(id, isPresent);
    }

    @Override
    public <S extends Attendance> List<S> saveAll(Iterable<S> entities) {
        return attendanceRepository.saveAll(entities);
    }

    @Override
    public <S extends Attendance> S save(S entity) {
        return attendanceRepository.save(entity);
    }

    @Override
    public Optional<Attendance> findById(Integer integer) {
        return attendanceRepository.findById(integer);
    }
}
