package org.jio.fyoga.service;/*  Welcome to Jio word
    @author: Jio
    Date: 6/30/2023
    Time: 11:37 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Register;
import org.jio.fyoga.model.MonthlyTotal;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRegisterService {
    <S extends Register> S save(S entity);

    List<Register> findAllByByCustomer_AccountID(int AccountID);

    List<Register> findByCustomer_AccountIDAndStatus(int AccountID, int status);

    List<MonthlyTotal> getMonthlyRegisterAmount();

    List<Register> findByStatusOrStatus(int statusPayingDone, int statusPayingUsing);

    Register findTopByStatusOrderByRegisteredDateDesc(int status);

    Register findFirstByStatusOrderByRegisteredDateAsc(int status);

    Register findByStatus(int status);

    Register findRegisterByStatusAndcourseID(int status, int courseID, int accoutID);

    Register findTopByStatusAndCourseIDOrderByRegisteredDateDesc(int status, int courseID,int accountID);

    Register findFirstByStatusAndCourseIDOrderByRegisteredDateAsc(int status,int courseID, int accountID);

    <S extends Register> List<S> saveAll(Iterable<S> entities);

    List<Register> findAllByStatusOrderByRegisteredDateDesc(int status);

    List<Register> findAllByStatusOrStatusOrderByRegisteredDateDesc(int statusv1, int statusv2);

    Register findById(Integer integer);

    List<Register> findAllByOrderByRegisteredDateDesc();
}
