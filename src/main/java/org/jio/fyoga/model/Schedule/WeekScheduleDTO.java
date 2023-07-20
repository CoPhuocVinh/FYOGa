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
    ActivityClassDTO t2;
    ActivityClassDTO t3;
    ActivityClassDTO t4;
    ActivityClassDTO t5;
    ActivityClassDTO t6;
    ActivityClassDTO t7;
    Slot slotID;
}
