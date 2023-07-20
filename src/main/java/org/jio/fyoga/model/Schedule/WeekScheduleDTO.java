package org.jio.fyoga.model.Schedule;/*  Welcome to Jio word
    @author: Jio
    Date: 7/21/2023
    Time: 1:50 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jio.fyoga.entity.ActivityClass;
import org.jio.fyoga.entity.Slot;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeekScheduleDTO {
    ActivityClass t2;
    ActivityClass t3;
    ActivityClass t4;
    ActivityClass t5;
    ActivityClass t6;
    ActivityClass t7;
    Slot slotID;
}
