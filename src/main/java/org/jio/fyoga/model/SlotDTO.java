package org.jio.fyoga.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SlotDTO {
    int slotID;
    String slotName;
    Time startTime;
    Time endTime;
    String staff;

}
