package org.jio.fyoga.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Schedule")

public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int scheduleID;

    @Column(name = "year")
    int year;

    @Column(name = "start_week")
    Date startWeek;

    @Column(name = "end_week")
    Date endWeek;

    @Column(name = "no_week")
    int noWeek;

//    @OneToMany(mappedBy = "schedule",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    Set<ScheduleClass> scheduleClasses;
}
