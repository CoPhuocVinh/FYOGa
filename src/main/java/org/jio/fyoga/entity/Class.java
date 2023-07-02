package org.jio.fyoga.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int classID;

    @Column(name = "status")
    int status;

    @Column(name = "quantity_class")
    int quantityClass;

    @Column(name = "create_day")
    Date createDay;

    @Column(name = "class_name", columnDefinition = "nvarchar(20)")
    String className;


    @OneToMany(mappedBy = "aClass",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<ScheduleClass> scheduleClasses;

    @OneToMany(mappedBy = "aClassBooking",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Booking> classBooking;

    @ManyToOne
    @JoinColumn(name = "teacherID")
    Account teacher;

    @ManyToOne
    @JoinColumn(name = "staffID")
    Account staff;

    @ManyToOne
    @JoinColumn(name = "courseID")
    Course course;

    @OneToMany(mappedBy = "aClassAttend",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Attendance> attendancesClass;

}
