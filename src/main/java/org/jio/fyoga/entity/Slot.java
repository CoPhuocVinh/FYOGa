package org.jio.fyoga.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
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

    @Column(name = "start_time")
    Time startTime;

    @Column(name = "end_time")
    Time endTime;

//    @OneToMany(mappedBy = "slot",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    Set<ScheduleClass> scheduleClasses;

    @ManyToOne
    @JoinColumn(name = "staffID")
    Account staff;

}
