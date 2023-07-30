package org.jio.fyoga.entity;/*  Welcome to Jio word
    @author: Jio
    Date: 7/12/2023
    Time: 12:35 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Builder
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Discount")
public class Discount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int discountID;

    @Column(name = "time_on_month")
    int timeOnMonth;

    @Column(name = "percent_discount")
    float percentDiscount;

    @OneToMany(mappedBy = "aDiscount", cascade = CascadeType.ALL)
    List<Register> registers;

    @ManyToOne
    @JoinColumn(name = "packageID")
    Package aPackage;
}
