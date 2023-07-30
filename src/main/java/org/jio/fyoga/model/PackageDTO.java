package org.jio.fyoga.model;/*  Welcome to Jio word
    @author: Jio
    Date: 6/25/2023
    Time: 12:27 AM
    
    ProjectName: demoSpring02
    Jio: I wish you always happy with coding <3
*/

import lombok.*;
import org.jio.fyoga.entity.Course;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PackageDTO {
    int packageID;
    String name;
    int slotOnMonth;
    int status;
    float price;
    int courseID;

    float priceDiscount;
    private Boolean isEdit = false;


}
