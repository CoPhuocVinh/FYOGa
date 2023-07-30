package org.jio.fyoga.entity;/*  Welcome to Jio word
    @author: Jio
    Date: 6/11/2023
    Time: 7:36 PM
    
    ProjectName: demoSpring
    Jio: I wish you always happy with coding <3
*/

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleID")
    private int roleID;

    @Column(name = "role_name", columnDefinition = "nvarchar(20)")
    private String roleName;

    //create relationShip
    @OneToMany(mappedBy = "role")
    private Set<Account> accounts;
}
