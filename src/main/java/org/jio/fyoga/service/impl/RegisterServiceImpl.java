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
}
