package org.jio.fyoga.service.impl;/*  Welcome to Jio word
    @author: Jio
    Date: 7/21/2023
    Time: 2:01 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.ActivityClass;
import org.jio.fyoga.entity.Booking;
import org.jio.fyoga.model.Schedule.ActivityClassDTO;
import org.jio.fyoga.model.Schedule.WeekScheduleDTO;
import org.jio.fyoga.repository.ActivityClassReponsitory;
import org.jio.fyoga.service.IActivityClassService;
import org.jio.fyoga.service.IBookingService;
import org.jio.fyoga.service.ISlotService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityClassServiceImpl implements IActivityClassService {
    @Autowired
    ActivityClassReponsitory activityClassReponsitory;
    @Autowired
    ISlotService slotService;

    @Autowired
    IBookingService bookingService;

    public ActivityClassServiceImpl(ActivityClassReponsitory activityClassReponsitory) {
        this.activityClassReponsitory = activityClassReponsitory;
    }

    public List<WeekScheduleDTO> getActivityClassesFromMondayToSaturday( int scheduleID) {
        List<WeekScheduleDTO> weekScheduleDTOS = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            List<Object[]> results = activityClassReponsitory.findActivityClassesFromMondayToSaturday(i, scheduleID);
            ActivityClassDTO t2 = ActivityClassDTO.builder().dayOfWeek(2).build();
            ActivityClassDTO t3 = ActivityClassDTO.builder().dayOfWeek(3).build();
            ActivityClassDTO t4 =  ActivityClassDTO.builder().dayOfWeek(4).build();
            ActivityClassDTO t5 =  ActivityClassDTO.builder().dayOfWeek(5).build();
            ActivityClassDTO t6 =  ActivityClassDTO.builder().dayOfWeek(6).build();
            ActivityClassDTO t7 =  ActivityClassDTO.builder().dayOfWeek(7).build();
            for (Object[] result : results){
                if ((Integer)result[0] == 2) {
                    ActivityClass activityClassEntity = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                    if(activityClassEntity != null){
                        BeanUtils.copyProperties(activityClassEntity, t2);
                        t2.setNoBooking(bookingService.countByAClassBooking_ClassID(activityClassEntity.getAClass().getClassID()));
                    }



                }
                if ((Integer)result[0] == 3) {
                    ActivityClass activityClassEntity = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                    if(activityClassEntity != null){
                        BeanUtils.copyProperties(activityClassEntity, t3);
                        t3.setNoBooking(bookingService.countByAClassBooking_ClassID(activityClassEntity.getAClass().getClassID()));

                    }


                }
                if ((Integer)result[0] == 4) {
                    ActivityClass activityClassEntity = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                    if(activityClassEntity != null){
                        BeanUtils.copyProperties(activityClassEntity, t4);
                        t4.setNoBooking(bookingService.countByAClassBooking_ClassID(activityClassEntity.getAClass().getClassID()));

                    }

                 //   activityClassDTOS.add(t4);
                }
                if ((Integer)result[0] == 5) {
                    ActivityClass activityClassEntity = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                    if(activityClassEntity != null){
                        BeanUtils.copyProperties(activityClassEntity, t5);
                        t5.setNoBooking(bookingService.countByAClassBooking_ClassID(activityClassEntity.getAClass().getClassID()));

                    }

                    //activityClassDTOS.add(t5);
                }
                if ((Integer)result[0] == 6) {
                    ActivityClass activityClassEntity = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                    if(activityClassEntity != null){
                        BeanUtils.copyProperties(activityClassEntity, t6);
                        t6.setNoBooking(bookingService.countByAClassBooking_ClassID(activityClassEntity.getAClass().getClassID()));

                    }

                    //activityClassDTOS.add(t6);
                }
                if ((Integer)result[0] == 7) {
                    ActivityClass activityClassEntity = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                    if(activityClassEntity != null){
                        BeanUtils.copyProperties(activityClassEntity, t7);
                        t7.setNoBooking(bookingService.countByAClassBooking_ClassID(activityClassEntity.getAClass().getClassID()));

                    }

                    //activityClassDTOS.add(t7);
                }

            }
            List<ActivityClassDTO> activityClassDTOS = new ArrayList<>();
            activityClassDTOS.add(t2);
            activityClassDTOS.add(t3);
            activityClassDTOS.add(t4);
            activityClassDTOS.add(t5);
            activityClassDTOS.add(t6);
            activityClassDTOS.add(t7);

            weekScheduleDTOS.add(new WeekScheduleDTO(activityClassDTOS,slotService.findById(i)));

        }

        return weekScheduleDTOS;
    }

    public List<WeekScheduleDTO> getActivityClassesFromMondayToSaturdayOnCourse( int scheduleID, int courseID, int userId) {
        List<WeekScheduleDTO> weekScheduleDTOS = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            List<Object[]> results = activityClassReponsitory.findActivityClassesFromMondayToSaturdayOnCourse(i, scheduleID, courseID);
            ActivityClassDTO t2 = ActivityClassDTO.builder().dayOfWeek(2).build();
            ActivityClassDTO t3 = ActivityClassDTO.builder().dayOfWeek(3).build();
            ActivityClassDTO t4 =  ActivityClassDTO.builder().dayOfWeek(4).build();
            ActivityClassDTO t5 =  ActivityClassDTO.builder().dayOfWeek(5).build();
            ActivityClassDTO t6 =  ActivityClassDTO.builder().dayOfWeek(6).build();
            ActivityClassDTO t7 =  ActivityClassDTO.builder().dayOfWeek(7).build();
            for (Object[] result : results){
                if ((Integer)result[0] == 2) {
                    ActivityClass activityClassEntity = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                    if(activityClassEntity != null)
                        BeanUtils.copyProperties(activityClassEntity, t2);
                        t2.setNoBooking(bookingService.countByAClassBooking_ClassID(activityClassEntity.getAClass().getClassID()));
                        Booking booking = bookingService.findByaClassBooking_ClassIDAndCustomer_AccountID(activityClassEntity.getAClass().getClassID(),userId);
                    if (booking!= null){
                            t2.setBooking(true);
                            t2.setExpired(booking.getExpired());
                        }else
                            t2.setBooking(false);
                }
                if ((Integer)result[0] == 3) {
                    ActivityClass activityClassEntity = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                    if(activityClassEntity != null)
                        BeanUtils.copyProperties(activityClassEntity, t3);
                    t3.setNoBooking(bookingService.countByAClassBooking_ClassID(activityClassEntity.getAClass().getClassID()));
                    Booking booking = bookingService.findByaClassBooking_ClassIDAndCustomer_AccountID(activityClassEntity.getAClass().getClassID(),userId);

                    if (booking != null){
                        t3.setBooking(true);
                        t3.setExpired(booking.getExpired());
                    }else
                        t3.setBooking(false);

                }
                if ((Integer)result[0] == 4) {
                    ActivityClass activityClassEntity = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                    if(activityClassEntity != null)
                        BeanUtils.copyProperties(activityClassEntity, t4);
                    //   activityClassDTOS.add(t4);
                    t4.setNoBooking(bookingService.countByAClassBooking_ClassID(activityClassEntity.getAClass().getClassID()));
                    Booking booking = bookingService.findByaClassBooking_ClassIDAndCustomer_AccountID(activityClassEntity.getAClass().getClassID(),userId);

                    if (booking != null){
                        t4.setBooking(true);
                        t4.setExpired(booking.getExpired());

                    }else
                        t4.setBooking(false);
                }
                if ((Integer)result[0] == 5) {
                    ActivityClass activityClassEntity = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                    if(activityClassEntity != null)
                        BeanUtils.copyProperties(activityClassEntity, t5);
                    //activityClassDTOS.add(t5);
                    t5.setNoBooking(bookingService.countByAClassBooking_ClassID(activityClassEntity.getAClass().getClassID()));
                    Booking booking = bookingService.findByaClassBooking_ClassIDAndCustomer_AccountID(activityClassEntity.getAClass().getClassID(),userId);
                    //
                    if (booking != null){
                        t5.setBooking(true);
                        t5.setExpired(booking.getExpired());

                    }else
                        t5.setBooking(false);
                }
                if ((Integer)result[0] == 6) {
                    ActivityClass activityClassEntity = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                    if(activityClassEntity != null)
                        BeanUtils.copyProperties(activityClassEntity, t6);
                    //activityClassDTOS.add(t6);
                    t6.setNoBooking(bookingService.countByAClassBooking_ClassID(activityClassEntity.getAClass().getClassID()));
                    Booking booking = bookingService.findByaClassBooking_ClassIDAndCustomer_AccountID(activityClassEntity.getAClass().getClassID(),userId);
                    //
                    if (booking != null){
                        t6 .setBooking(true);
                        t6.setExpired(booking.getExpired());

                    }else
                        t6.setBooking(false);
                }
                if ((Integer)result[0] == 7) {
                    ActivityClass activityClassEntity = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                    if(activityClassEntity != null)
                        BeanUtils.copyProperties(activityClassEntity, t7);
                    //activityClassDTOS.add(t7);
                    t7.setNoBooking(bookingService.countByAClassBooking_ClassID(activityClassEntity.getAClass().getClassID()));
                    Booking booking = bookingService.findByaClassBooking_ClassIDAndCustomer_AccountID(activityClassEntity.getAClass().getClassID(),userId);
                    //
                    if (booking != null){
                        t7.setBooking(true);
                        t7.setExpired(booking.getExpired());

                    }else
                        t7.setBooking(false);
                }

            }
            List<ActivityClassDTO> activityClassDTOS = new ArrayList<>();
            activityClassDTOS.add(t2);
            activityClassDTOS.add(t3);
            activityClassDTOS.add(t4);
            activityClassDTOS.add(t5);
            activityClassDTOS.add(t6);
            activityClassDTOS.add(t7);

            weekScheduleDTOS.add(new WeekScheduleDTO(activityClassDTOS,slotService.findById(i)));

        }

        return weekScheduleDTOS;
    }



    public <S extends ActivityClass> S save(S entity) {
        return activityClassReponsitory.save(entity);
    }

    public ActivityClass findById(Integer integer) {
        return activityClassReponsitory.findById(integer).orElseThrow();
    }
}
