package org.jio.fyoga.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.sql.Timestamp;


import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bookingID;

    @Column(name = "booking_date")
    Timestamp bookingDate;

    @Column(name = "status")
    int status;

    @ManyToOne
    @JoinColumn(name = "customerID")
    Account customer;

    @ManyToOne
    @JoinColumn(name = "classID")
    Class aClassBooking;

}
