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

    @Column(name = "title", columnDefinition = "nvarchar(30)")
    String title;

    @Column(name = "dessciption", columnDefinition = "nvarchar(MAX)")
    String dessciption;

    @Column(name = "img")
    @Lob
    private byte[] img;

    @Column(name = "author")
    String author;

    @Column(name = "status")
    int status;

    @Column(name = "create_day", columnDefinition = "datetime")
    private Date createDay;


//    @OneToMany(mappedBy = "aClass",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    Set<ScheduleClass> scheduleClasses;



}
