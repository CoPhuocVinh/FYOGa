package org.jio.fyoga.repository;/*  Welcome to Jio word
    @author: Jio
    Date: 7/14/2023
    Time: 4:53 AM

    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Booking;
import org.jio.fyoga.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT COUNT(b.bookingID) AS count FROM Booking b GROUP BY b.aClassBooking.classID")
    int countBookingIDsByClassID();

    Booking findByaClassBooking_ClassIDAndCustomer_AccountIDAndStatus(int classID, int userID, int status);

    int countByaClassBooking_ClassID(int classId);

    List<Booking> findAllByCustomer_AccountID(int id);

    List<Booking> findAllByaClassBooking_Course_CourseIDAndCustomer_AccountIDAndStatus(int courseID, int userID, int status);

    List<Booking> findAllByaClassBooking_ClassIDAndStatus(int classID, int status);

}
