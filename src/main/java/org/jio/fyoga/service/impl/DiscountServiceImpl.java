package org.jio.fyoga.service.impl;/*  Welcome to Jio word
    @author: Jio
    Date: 7/12/2023
    Time: 12:50 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Discount;
import org.jio.fyoga.repository.DiscountRepository;
import org.jio.fyoga.service.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements IDiscountService {

    @Autowired
    DiscountRepository discountRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public List<Discount> findAllByAPackage_PackageID(int PackageID) {
        return discountRepository.findAllByaPackage_PackageID(PackageID);
    }

    @Override
    public Optional<Discount> findById(Integer integer) {
        return discountRepository.findById(integer);
    }

    @Override
    public List<Discount> findAll() {
        return discountRepository.findAll();
    }
}
