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
public class AttendanceDTO {
    int attendanceID;
    Date attendanceDate;
    Boolean isPresent;
    String aClassAttend;
    String customer;
    String staff;

}
