package org.jio.fyoga.util;/*  Welcome to Jio word
    @author: Jio
    Date: 7/31/2023
    Time: 1:39 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Register;
import org.jio.fyoga.service.IRegisterService;
import org.jio.fyoga.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyUtilService {
    @Autowired
    IRegisterService registerService;

    public Register Confirm(Register registerEntity){

        //registerEntity = registerService.findById(registerID);
        if (registerEntity != null){
            registerEntity.setStatus(2);
            int courseID = registerEntity.getADiscount().getAPackage().getCourse().getCourseID();
            int noDateExpired = registerEntity.getTimeAvailable()*4*7;
            Register register02 = null;
            Register register03 = null;
            try{
                register02 = registerService.findTopByStatusAndCourseIDOrderByRegisteredDateDesc(2,courseID);
                register03 = registerService.findRegisterByStatusAndcourseID(3,courseID);
            }catch (Exception ex){

            }
            if (register03 != null){

                if (register02 != null){

                    java.util.Date dateExpired = MyUtil.expiredDateOnDate(register02.getExpired(),noDateExpired);
                    registerEntity.setExpired(dateExpired);
                }else {

                    java.util.Date dateExpired = MyUtil.expiredDateOnDate(register03.getExpired(),noDateExpired);
                    registerEntity.setExpired(dateExpired);
                }
            }else {
                if (register02 != null){

                    java.util.Date dateExpired = MyUtil.expiredDateOnDate(register02.getExpired(),noDateExpired);
                    registerEntity.setExpired(dateExpired);
                }else {

                    java.util.Date dateExpired = MyUtil.expiredDateOnDate(noDateExpired);
                    registerEntity.setExpired(dateExpired);
                }
            }




            registerService.save(registerEntity);
        }
        return registerEntity;
    }
}
