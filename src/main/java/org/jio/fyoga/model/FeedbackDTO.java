package org.jio.fyoga.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeedbackDTO {
    int feedbackID;
    private String name;
    private String email;
    private String comment;

    Integer status;
    Date date;
}
