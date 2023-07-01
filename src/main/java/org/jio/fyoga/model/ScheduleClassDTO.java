package org.jio.fyoga.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleClassDTO {
    int scheduleClassID;
    String scheduleClassName;
    int dayOfWeek;
    int status;
    Date dayOfMonth;
    String schedule;
    String slot;
    String aClass;
    String staff;

}
