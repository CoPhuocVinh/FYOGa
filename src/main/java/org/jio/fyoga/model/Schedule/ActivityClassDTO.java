package org.jio.fyoga.model.Schedule;/*  Welcome to Jio word
    @author: Jio
    Date: 7/21/2023
    Time: 3:23 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import lombok.*;
import org.jio.fyoga.entity.Class;
import org.jio.fyoga.entity.Schedule;
import org.jio.fyoga.entity.Slot;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ActivityClassDTO {
    int activityID;
    int dayOfWeek;
    Schedule schedule;
    Slot slot;
    Class aClass;
    int status;
    String note;
    Date dayOfMonth;

    int noBooking;
    Date expired;
    boolean isBooking = false;

    boolean isEdit = false;
}
