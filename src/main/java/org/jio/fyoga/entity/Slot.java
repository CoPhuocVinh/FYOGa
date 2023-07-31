package org.jio.fyoga.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Slot")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int slotID;

    @Column(name = "slot_name", columnDefinition = "nvarchar(10)")
    String slotName;

    @DateTimeFormat(pattern = "HH:mm")
    private Time startTime;

    @DateTimeFormat(pattern = "HH:mm")
    private Time endTime;

//    @OneToMany(mappedBy = "slot",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    Set<ScheduleClass> scheduleClasses;

    @Column(name =  "staffID")
    int staff;

}
