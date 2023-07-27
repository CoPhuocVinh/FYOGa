package org.jio.fyoga.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jio.fyoga.entity.Class;
import org.jio.fyoga.entity.Schedule;
import org.jio.fyoga.entity.Slot;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityCreateOrEditDTO {
    int activityID;
    int dayOfWeek;
    //Schedule schedule;
    int slotID;
    int aClassID;
    int status;
    String note;
    //Date dayOfMonth;

    boolean isEdit = false;

}
