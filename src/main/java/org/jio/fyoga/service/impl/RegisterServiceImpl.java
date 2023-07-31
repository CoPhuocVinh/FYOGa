package org.jio.fyoga.service.impl;/*  Welcome to Jio word
    @author: Jio
    Date: 6/30/2023
    Time: 11:38 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Register;
import org.jio.fyoga.model.MonthlyTotal;
import org.jio.fyoga.repository.RegisterRepository;
import org.jio.fyoga.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterServiceImpl implements IRegisterService {
    @Autowired
    RegisterRepository registerRepository;

    public RegisterServiceImpl(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @Override
    public <S extends Register> S save(S entity) {
        return registerRepository.save(entity);
    }

    @Override
    public List<Register> findAllByByCustomer_AccountID(int AccountID) {
        return registerRepository.findByCustomer_AccountID(AccountID);
    }

    @Override
    public List<Register> findByCustomer_AccountIDAndStatus(int AccountID, int status) {
        return registerRepository.findByCustomer_AccountIDAndStatus(AccountID, status);
    }

    @Override
    public List<MonthlyTotal> getMonthlyRegisterAmount() {
        List<Object[]> results = registerRepository.getMonthlyRegisterAmount();
        List<MonthlyTotal> monthlyTotalList = new ArrayList<>();
        for (Object[] result : results) {
            Integer month = (Integer) result[0];
            System.out.println(month);
            double totalAmount = (double) result[1];
            System.out.println(totalAmount);
            monthlyTotalList.add(new MonthlyTotal(month, totalAmount));
        }
        return monthlyTotalList;
    }

    @Override
    public List<Register> findByStatusOrStatus(int statusPayingDone, int statusPayingUsing) {
        return registerRepository.findByStatusOrStatus(statusPayingDone, statusPayingUsing);
    }

    @Override
    public Register findTopByStatusOrderByRegisteredDateDesc(int status) {
        return registerRepository.findTopByStatusOrderByRegisteredDateDesc(status);
    }

    @Override
    public Register findFirstByStatusOrderByRegisteredDateAsc(int status) {
        return registerRepository.findFirstByStatusOrderByRegisteredDateAsc(status);
    }

    @Override
    public Register findByStatus(int status) {
        return registerRepository.findByStatus(status);
    }

    @Override
    public Register findRegisterByStatusAndcourseID(int status, int courseID, int accoutID) {
        return registerRepository.findRegisterByStatusAndaDiscount_aPackage_course_courseID(status, courseID, accoutID);
    }


    //@Override
    public Register findTopByStatusAndCourseIDOrderByRegisteredDateDesc(int status, int courseID, int accountID) {
        List<Register> registerList = registerRepository.findTopByStatusAndADiscount_APackage_Course_CourseIDOrderByRegisteredDateDesc(status, courseID,accountID);
        return registerRepository.findTopByStatusAndADiscount_APackage_Course_CourseIDOrderByRegisteredDateDesc(status, courseID,accountID).get(0);
    }


    @Override
    public Register findFirstByStatusAndCourseIDOrderByRegisteredDateAsc(int status, int courseID, int accountID) {
        return registerRepository.findFirstByStatusAndADiscount_APackage_Course_CourseIDOrderByRegisteredDateAsc(status,courseID, accountID).get(0);
    }

    @Override
    public <S extends Register> List<S> saveAll(Iterable<S> entities) {
        return registerRepository.saveAll(entities);
    }

    @Override
    public List<Register> findAllByStatusOrderByRegisteredDateDesc(int status) {
        return registerRepository.findAllByStatusOrderByRegisteredDateDesc(status);
    }

    @Override
    public List<Register> findAllByStatusOrStatusOrderByRegisteredDateDesc(int statusv1, int statusv2) {
        return registerRepository.findAllByStatusOrStatusOrderByRegisteredDateDesc(statusv1, statusv2);
    }

    @Override
    public Register findById(Integer integer) {
        return registerRepository.findById(integer).orElse(null);
    }

    @Override
    public List<Register> findAllByOrderByRegisteredDateDesc() {
        return registerRepository.findAllByOrderByRegisteredDateDesc();
    }
}
