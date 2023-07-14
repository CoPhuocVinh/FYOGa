package org.jio.fyoga.service;/*  Welcome to Jio word
    @author: Jio
    Date: 7/14/2023
    Time: 4:55 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Booking;

public interface IBookingService {
    <S extends Booking> S save(S entity);


    Booking findByaClassBooking_ClassIDAndCustomer_AccountID(int classID, int userID);
}
