package org.jio.fyoga.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Activity_Class")

public class ActivityClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int activityID;
//
//    @Column(name = "schedule_class_name", columnDefinition = "nvarchar(20) not null")
//    String scheduleClassName;

    @Column(name = "day_of_week")
    int dayOfWeek;

    @Column(name = "status")
    int status;

    @Column(name = "note")
    String note;

    @Column(name = "day_of_month")
    Date dayOfMonth;

    @ManyToOne
    @JoinColumn(name = "scheduleID")
    Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "slotID")
    Slot slot;

    @ManyToOne
    @JoinColumn(name = "classID")
    Class aClass;

    @ManyToOne
    @JoinColumn(name = "staffID")
    Account staff;

    @OneToMany(mappedBy = "activityClass")
    List<Attendance> attendances;


}
