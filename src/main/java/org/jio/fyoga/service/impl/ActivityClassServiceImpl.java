package org.jio.fyoga.service.impl;/*  Welcome to Jio word
    @author: Jio
    Date: 7/21/2023
    Time: 2:01 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.ActivityClass;
import org.jio.fyoga.model.Schedule.ActivityClassDTO;
import org.jio.fyoga.model.Schedule.WeekScheduleDTO;
import org.jio.fyoga.repository.ActivityClassReponsitory;
import org.jio.fyoga.service.IActivityClassService;
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
                    if(activityClassEntity != null)
                        BeanUtils.copyProperties(activityClassEntity, t2);


                }
                if ((Integer)result[0] == 3) {
                    ActivityClass activityClassEntity = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                    if(activityClassEntity != null)
                        BeanUtils.copyProperties(activityClassEntity, t3);

                }
                if ((Integer)result[0] == 4) {
                    ActivityClass activityClassEntity = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                    if(activityClassEntity != null)
                        BeanUtils.copyProperties(activityClassEntity, t4);
                 //   activityClassDTOS.add(t4);
                }
                if ((Integer)result[0] == 5) {
                    ActivityClass activityClassEntity = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                    if(activityClassEntity != null)
                        BeanUtils.copyProperties(activityClassEntity, t5);
                    //activityClassDTOS.add(t5);
                }
                if ((Integer)result[0] == 6) {
                    ActivityClass activityClassEntity = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                    if(activityClassEntity != null)
                        BeanUtils.copyProperties(activityClassEntity, t6);
                    //activityClassDTOS.add(t6);
                }
                if ((Integer)result[0] == 7) {
                    ActivityClass activityClassEntity = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                    if(activityClassEntity != null)
                        BeanUtils.copyProperties(activityClassEntity, t7);
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

    public <S extends ActivityClass> S save(S entity) {
        return activityClassReponsitory.save(entity);
    }

    public ActivityClass findById(Integer integer) {
        return activityClassReponsitory.findById(integer).orElseThrow();
    }
}
