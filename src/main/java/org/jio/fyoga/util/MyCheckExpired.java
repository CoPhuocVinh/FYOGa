package org.jio.fyoga.util;/*  Welcome to Jio word
    @author: Jio
    Date: 7/28/2023
    Time: 7:58 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import lombok.NoArgsConstructor;
import org.jio.fyoga.entity.Booking;
import org.jio.fyoga.entity.Register;
import org.jio.fyoga.service.IBookingService;
import org.jio.fyoga.service.IRegisterService;
import org.jio.fyoga.service.impl.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
@Controller
@NoArgsConstructor
public class MyCheckExpired {

    public static List<Register> checkExpiredOnRegister(List<Register> registers){
        Date currentDate = MyUtil.currentDate();
        for (Register register :registers){
            if (currentDate.after(register.getExpired())){
                register.setStatus(0);
            }
        }

        return registers;
    }

    public static List<Booking> checkExpiredOnBooking(List<Booking> bookings){
        Date currentDate = MyUtil.currentDate();
        for (Booking booking :bookings){
            if (currentDate.after(booking.getExpired())){
                booking.setStatus(0);
            }else {

                booking.setStatus(1);
            }
        }
        return bookings;
    }

    public static void checkExpiredOnBookingv1(List<Booking> bookings){
        bookings = checkExpiredOnBooking(bookings);
    }
    public static void checkExpiredOnRegisterv1(List<Register> registers){
        registers = checkExpiredOnRegister(registers);
    }

    public static void checkExpired(List<Booking> bookings, List<Register> registers){
        checkExpiredOnBookingv1(bookings);
        checkExpiredOnRegisterv1(registers);

    }


}
