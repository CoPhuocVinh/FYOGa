package org.jio.fyoga.model;/*  Welcome to Jio word
    @author: Jio
    Date: 6/22/2023
    Time: 11:07 PM
    
    ProjectName: demoSpring02
    Jio: I wish you always happy with coding <3
*/

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseDTO {
    private int courseID;

    private String summary;

    private String description;

    private String name;

    private byte[] img;
    private int AdminID;

    private Boolean isEdit = false;

}
