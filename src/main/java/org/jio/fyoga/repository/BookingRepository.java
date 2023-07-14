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

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT COUNT(b.bookingID) AS count FROM Booking b GROUP BY b.aClassBooking.classID")
    int countBookingIDsByClassID();

    Booking findByaClassBooking_ClassIDAndCustomer_AccountID(int classID, int userID);

}
