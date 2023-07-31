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

import java.util.List;

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
        return bookingRepository.findByaClassBooking_ClassIDAndCustomer_AccountIDAndStatus(classID, userID, 1);
    }

    @Override
    public int countByAClassBooking_ClassID(int classId) {
        return bookingRepository.countByaClassBooking_ClassID(classId);
    }

    @Override
    public List<Booking> findAllByCustomer_AccountID(int id) {
        return bookingRepository.findAllByCustomer_AccountID(id);
    }

    @Override
    public <S extends Booking> List<S> saveAll(Iterable<S> entities) {
        return bookingRepository.saveAll(entities);
    }

    @Override
    public List<Booking> findAllByaClassBooking_Course_CourseIDAndCustomer_AccountIDAndStatus(int courseID, int userID, int status) {
        return bookingRepository.findAllByaClassBooking_Course_CourseIDAndCustomer_AccountIDAndStatus(courseID, userID, status);
    }

    @Override
    public List<Booking> findAllByAClassBooking_ClassIDAndStatus(int classID, int status) {
        return bookingRepository.findAllByaClassBooking_ClassIDAndStatus(classID, status);
    }
}
