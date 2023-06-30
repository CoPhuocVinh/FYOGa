package org.jio.fyoga.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Register{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int registerID;

    @Column(name = "slot_available")
    int slotAvailable;

    @Column(name = "time_available")
    int timeAvailable;

    @Column(name = "registered_date")
    Date registeredDate;

    @Column(name = "status")
    int status;

    @Column(name = "price_original")
    float priceOriginal;

    @Column(name = "price_discount")
    float priceDiscount;

    @Column(name = "slot_used")
    int slotUsed;

    @Column(name = "week_used")
    int weekUsed;

    @ManyToOne
    @JoinColumn(name = "packageID")
    Package packages;

    @ManyToOne
    @JoinColumn(name = "customerID")
    Account customer;

}
