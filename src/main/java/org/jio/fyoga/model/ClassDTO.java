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
public class ClassDTO {

    int classID;
    int status;
    int quantityClass;
    Date createDay;
    String className;
    int teacherID;
    int staffID;
    int courseID;

    Boolean isEdit = false;
}
