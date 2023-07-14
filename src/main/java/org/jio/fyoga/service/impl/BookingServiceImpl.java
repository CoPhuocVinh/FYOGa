package org.jio.fyoga.service.impl;/*  Welcome to Jio word
    @author: Jio
    Date: 7/14/2023
    Time: 4:54 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Booking;
import org.jio.fyoga.repository.BookingRepository;
import org.jio.fyoga.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements IBookingService {
    @Autowired
    BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public <S extends Booking> S save(S entity) {
        return bookingRepository.save(entity);
    }

    @Override
    public Booking findByaClassBooking_ClassIDAndCustomer_AccountID(int classID, int userID) {
        return bookingRepository.findByaClassBooking_ClassIDAndCustomer_AccountID(classID, userID);
    }

}
