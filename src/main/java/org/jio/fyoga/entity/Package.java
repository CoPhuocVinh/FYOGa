package org.jio.fyoga.entity;/*  Welcome to Jio word
    @author: Jio
    Date: 6/24/2023
    Time: 11:03 PM
    
    ProjectName: demoSpring02
    Jio: I wish you always happy with coding <3
*/

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int packageID;

    @Column(name = "package_name", columnDefinition = "nvarchar(50)")
    String name;

    @Column(name = "slot_on_month")
    int slotOnMonth;

    int status;

    @Column(name = "price")
    float price;

    @ManyToOne
    @JoinColumn(name = "courseID")
    Course course;

    @OneToMany(mappedBy = "aPackage",fetch = FetchType.EAGER)
    List<Discount> discounts;




}
