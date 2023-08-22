package org.jio.fyoga.model;/*  Welcome to Jio word
    @author: Jio
    Date: 7/12/2023
    Time: 1:04 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDTO {
    int discountID;

    int timeOnMonth;

    float percentDiscount;

    int packageID;

    float priceDiscount;
    int status;

    int slotOnMonth;
    private Boolean isEdit = false;


}
