package org.jio.fyoga.entity;/*  Welcome to Jio word
    @author: Jio
    Date: 6/22/2023
    Time: 10:23 PM
    
    ProjectName: demoSpring02
    Jio: I wish you always happy with coding <3
*/

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseID")
    private int courseID;

    @Column(name = "summary", columnDefinition = "nvarchar(100) not null")
    private String summary;

    @Column(name = "description", columnDefinition = "nvarchar(MAX) not null")
    private String description;

    @Column(name = "course_name", columnDefinition = "nvarchar(50) not null")
    private String name;

    @Column(name = "img")
    @Lob
    private byte[] img;

    @ManyToOne
    @JoinColumn(name = "adminID")
    private Account admin;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private Set<Package> packages;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private Set<Class> classes;

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", name='" + name + '\'' +
                // Thêm các trường khác vào đây nếu cần thiết
                '}';
    }
}
