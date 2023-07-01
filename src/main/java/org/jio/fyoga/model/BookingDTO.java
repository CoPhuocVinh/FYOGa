package org.jio.fyoga.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class BookingDTO {
    int bookingID;
    Timestamp bookingDate;
    int status;
    String customer;
    String aClassBooking;
}
