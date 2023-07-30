package org.jio.fyoga.entity;/*  Welcome to Jio word
    @author: Jio
    Date: 6/11/2023
    Time: 7:49 PM
    
    ProjectName: demoSpring
    Jio: I wish you always happy with coding <3
*/

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Account")
@Builder
public class Account implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountID")
    private int accountID;

    @Column(name = "email", columnDefinition = "nvarchar(MAX) not null")
    private String email;

    @Column(name = "password", columnDefinition = "nvarchar(50) not null")
    private String password;

    @Column(name = "status")
    private int status;

    @Column(name = "full_name", columnDefinition = "nvarchar(MAX) not null")
    private String fullName;

    @Column(name = "phone", columnDefinition = "char(15)")
    private String phone;

    @Column(name = "avatar")
    @Lob
    private byte[] avatar;

    @Column(name = "gender", columnDefinition = "char(5)")
    private String gender;

    @Column(name = "accepted_date", columnDefinition = "date")
    private Date acceptedDate;

    //ket noi many to one vs ban role
    @ManyToOne
    @JoinColumn(name = "roleID")
    private Role role;

    @OneToMany(mappedBy = "admin")
    private Set<Course> courses;

    @OneToMany(mappedBy = "customer")
    private Set<Register> registers;

    @OneToMany(mappedBy = "customer")
    private Set<Booking> bookings;

    @OneToMany(mappedBy = "customer")
    private Set<Attendance> attendances;

    @OneToMany(mappedBy = "teacher")
    private Set<Class> classes;

}
