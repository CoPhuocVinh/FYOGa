package org.jio.fyoga.model.Schedule;/*  Welcome to Jio word
    @author: Jio
    Date: 7/20/2023
    Time: 11:52 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WeekDTO {
    int dayOfWeek;
    String dayOfMonth;
    int year;
    int weekOfYear;

    public void setDayOfMonthInDateFormat(Date  date ) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.dayOfMonth = sdf.format(date);
    }

}
