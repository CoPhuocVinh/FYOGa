package org.jio.fyoga.repository;/*  Welcome to Jio word
    @author: Jio
    Date: 7/20/2023
    Time: 11:10 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    // Ví dụ: Tìm các lịch học theo năm và số tuần
    List<Schedule> findByYearAndNoWeek(int year, int noWeek);

    // Ví dụ: Tìm các lịch học theo năm và số tuần nằm trong khoảng từ weekFrom đến weekTo
    List<Schedule> findByYearAndNoWeekBetween(int year, int weekFrom, int weekTo);
    Schedule findScheduleByYearAndNoWeek(int year, int noWeek);


}
