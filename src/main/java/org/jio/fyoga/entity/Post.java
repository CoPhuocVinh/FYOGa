package org.jio.fyoga.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Post")
public class Post {
    @Id
    @Column(name = "postID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int postID;

    @Column(name = "post_name")
    String postName;

    @Column(name = "dessciption")
    String dessciption;




//    @OneToMany(mappedBy = "aClass",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    Set<ScheduleClass> scheduleClasses;



    @ManyToOne
    @JoinColumn(name = "staffID")
    Account staff;


}
