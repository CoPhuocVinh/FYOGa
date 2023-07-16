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
public class RegisterDTO {
    int registerID;
    int slotAvailable;
    int timeAvailable;
    Date registeredDate;
    int status;
    float priceOriginal;
    float priceDiscount;
    int slotUsed;
    int weekUsed;
    int customerID;
    int aDiscountID;
    int typePaying;
}
