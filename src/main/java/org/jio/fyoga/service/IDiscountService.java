package org.jio.fyoga.service;/*  Welcome to Jio word
    @author: Jio
    Date: 7/12/2023
    Time: 12:50 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Discount;

import java.util.List;
import java.util.Optional;

public interface IDiscountService {
    List<Discount> findAllByAPackage_PackageID(int PackageID);

    Optional<Discount> findById(Integer integer);

    void save(Discount discount);
    List<Discount> findAll();

    void deleteById(int discountID);

    List<Discount> findDiscountByaPackage_PackageID(int packageID);
}
