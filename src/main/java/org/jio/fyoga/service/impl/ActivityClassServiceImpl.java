package org.jio.fyoga.service.impl;/*  Welcome to Jio word
    @author: Jio
    Date: 7/21/2023
    Time: 2:01 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.ActivityClass;
import org.jio.fyoga.model.Schedule.WeekScheduleDTO;
import org.jio.fyoga.repository.ActivityClassReponsitory;
import org.jio.fyoga.service.IActivityClassService;
import org.jio.fyoga.service.ISlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            ActivityClass t2 = null;
            ActivityClass t3 = null;
            ActivityClass t4 = null;
            ActivityClass t5 = null;
            ActivityClass t6 = null;
            ActivityClass t7 = null;
            for (Object[] result : results){
                if ((Integer)result[0] == 2) {
                    t2 = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                }
                if ((Integer)result[0] == 3) {
                    t3 = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                }
                if ((Integer)result[0] == 4) {
                    t4 = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                }
                if ((Integer)result[0] == 5) {
                    t5 = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                }
                if ((Integer)result[0] == 6) {
                    t6 = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                }
                if ((Integer)result[0] == 7) {
                    t7 = activityClassReponsitory.findById((Integer) result[1]).orElse(null);
                }

            }

            weekScheduleDTOS.add(new WeekScheduleDTO(t2,t3,t4,t5,t6,t7,slotService.findById(i)));

        }

        return weekScheduleDTOS;
    }
}
