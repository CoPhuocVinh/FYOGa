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

    @Column(name = "summary")
    private String summary;

    @Column(name = "description")
    private String description;

    @Column(name = "course_name")
    private String name;

    @Column(name = "img")
    @Lob
    private byte[] img;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "adminID")
    private Account admin;

    @OneToMany(mappedBy = "course",fetch = FetchType.EAGER)
    private Set<Package> packages;

    @OneToMany(mappedBy = "course",fetch = FetchType.EAGER)
    private Set<Class> classes;

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", name='" + name + '\'' +
                '}';
    }
}
