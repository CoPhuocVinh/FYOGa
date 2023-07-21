package org.jio.fyoga.service;/*  Welcome to Jio word
    @author: Jio
    Date: 7/20/2023
    Time: 11:11 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Schedule;

import java.util.List;

public interface IScheduleService {
    void createNextWeekScheduleIfNotExists();

    List<Schedule> findByYearAndNoWeek(int year, int noWeek);
}
