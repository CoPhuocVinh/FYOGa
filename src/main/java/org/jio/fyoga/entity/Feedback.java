package org.jio.fyoga.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int feedbackID;
    @Column(name = "name")
    Integer name;
    @Column(name = "comment", columnDefinition = "nvarchar(300)")
    String comment;
    @Column(name = "status")
    Integer status;
    @Column(name = "date", columnDefinition = "nvarchar(100)")
    Date date;
    @Column(name = "email", columnDefinition = "nvarchar(50)")
    String email;

//    @OneToMany(mappedBy = "slot",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    Set<ScheduleClass> scheduleClasses;



}
