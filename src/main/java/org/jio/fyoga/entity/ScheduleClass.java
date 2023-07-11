//package org.jio.fyoga.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//
//import java.sql.Date;
//
//@Entity
//@Getter
//@Setter
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "Schedule_Class")
//
//public class ScheduleClass {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    int scheduleClassID;
//
//    @Column(name = "schedule_class_name", columnDefinition = "nvarchar(20) not null")
//    String scheduleClassName;
//
//    @Column(name = "day_of_week")
//    int dayOfWeek;
//
//    @Column(name = "status")
//    int status;
//
//    @Column(name = "day_of_month")
//    Date dayOfMonth;
//
//    @ManyToOne
//    @JoinColumn(name = "scheduleID")
//    Schedule schedule;
//
//    @ManyToOne
//    @JoinColumn(name = "slotID")
//    Slot slot;
//
//    @ManyToOne
//    @JoinColumn(name = "classID")
//    Class aClass;
//
//    @ManyToOne
//    @JoinColumn(name = "staffID")
//    Account staff;
//}
