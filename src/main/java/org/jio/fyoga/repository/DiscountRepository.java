package org.jio.fyoga.repository;/*  Welcome to Jio word
    @author: Jio
    Date: 7/12/2023
    Time: 12:48 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/


import org.jio.fyoga.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {

    List<Discount> findAllByaPackage_PackageID(int PackageID);
}
