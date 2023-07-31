package org.jio.fyoga.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendanceID")
    int attendanceID;

    @Column(name = "is_present")
    Boolean isPresent;

    @Column(name = "staffID")
    int staff;

    @ManyToOne
    @JoinColumn(name = "customerID")
    Account customer;


    @ManyToOne
    @JoinColumn(name = "activityID")
    ActivityClass activityClass;

}
