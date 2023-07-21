package org.jio.fyoga.service.impl;/*  Welcome to Jio word
    @author: Jio
    Date: 7/20/2023
    Time: 11:11 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Schedule;
import org.jio.fyoga.model.Schedule.WeekDTO;
import org.jio.fyoga.repository.ScheduleRepository;
import org.jio.fyoga.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImpl implements IScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
    @Override
    public void createNextWeekScheduleIfNotExists() {
        // Xác định tuần hiện tại
        Calendar currentWeek = Calendar.getInstance();
        int currentYear = currentWeek.get(Calendar.YEAR);
        int currentWeekNumber = currentWeek.get(Calendar.WEEK_OF_YEAR);

        // Kiểm tra xem đã có lịch học cho tuần tiếp theo hay chưa
        List<Schedule> nextWeekSchedules = scheduleRepository.findByYearAndNoWeek(currentYear, currentWeekNumber + 1);

        if (nextWeekSchedules.isEmpty()) {
            // Tạo mới lịch học cho tuần tiếp theo
            Schedule schedule = new Schedule();
            schedule.setYear(currentYear);
            schedule.setNoWeek(currentWeekNumber + 1);

            // Tính toán ngày bắt đầu và kết thúc của tuần
            currentWeek.add(Calendar.WEEK_OF_YEAR, 1);
            // Đặt ngày trong tuần là Thứ Hai (Monday)
            currentWeek.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            Date startOfWeek = currentWeek.getTime();

            // Đặt ngày trong tuần là Thứ Bảy (Saturday)
            currentWeek.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            Date endOfWeek = currentWeek.getTime();

            schedule.setStartWeek(startOfWeek);
            schedule.setEndWeek(endOfWeek);

            scheduleRepository.save(schedule);
        }
    }

    public List<WeekDTO> WeekNow(){
        // Xác định tuần hiện tại
        Calendar currentWeek = Calendar.getInstance();
        //currentWeek.set(Calendar.WEEK_OF_YEAR,28);
        int currentYear = currentWeek.get(Calendar.YEAR);
        int currentWeekNumber = currentWeek.get(Calendar.WEEK_OF_YEAR);
        currentWeek.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        List<WeekDTO> weekDTOS= new ArrayList<>();
        // Kiểm tra xem đã có lịch học cho tuần hiện tại chưa
        List<Schedule> nextWeekSchedules = scheduleRepository.findByYearAndNoWeek(currentYear, currentWeekNumber);

        //nếu chưa có
        if (nextWeekSchedules.isEmpty()) {
            // Tạo mới lịch học cho tuần hiện tại
            Schedule schedule = new Schedule();
            schedule.setYear(currentYear);
            schedule.setNoWeek(currentWeekNumber);
            for (int i = 2; i <= 7; i++){
                WeekDTO weekDTO = new WeekDTO();
                if (i == 2){
                    Date startOfWeek = currentWeek.getTime();
                    schedule.setStartWeek(startOfWeek);
                }
                if (i == 7){
                    Date endOfWeek = currentWeek.getTime();
                    schedule.setEndWeek(endOfWeek);
                }

                weekDTO.setDayOfMonthInDateFormat(currentWeek.getTime());
                weekDTO.setDayOfWeek(i);
                weekDTO.setWeekOfYear(currentWeekNumber);
                weekDTO.setYear(currentYear);
                currentWeek.add(Calendar.DAY_OF_MONTH,1);

                weekDTOS.add(weekDTO);
            }
            scheduleRepository.save(schedule);

        }// end if
        else {
            for (int i = 2; i <= 7; i++){
                WeekDTO weekDTO = new WeekDTO();
                weekDTO.setDayOfMonthInDateFormat(currentWeek.getTime());
                weekDTO.setDayOfWeek(i);
                weekDTO.setWeekOfYear(currentWeekNumber);
                weekDTO.setYear(currentYear);
                currentWeek.add(Calendar.DAY_OF_MONTH,1);

                weekDTOS.add(weekDTO);
            }

        }



        return weekDTOS;
    }

    public List<WeekDTO> WeekChange(int noChange){
        // Xác định tuần hiện tại
        Calendar currentWeek = Calendar.getInstance();
        int currentYear = currentWeek.get(Calendar.YEAR);

        currentWeek.set(Calendar.WEEK_OF_YEAR, noChange);

        currentYear = currentWeek.get(Calendar.YEAR);
        int currentWeekNumber = currentWeek.get(Calendar.WEEK_OF_YEAR);
        currentWeek.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        List<WeekDTO> weekDTOS= new ArrayList<>();
        // Kiểm tra xem đã có lịch học cho tuần hiện tại chưa
        List<Schedule> WeekSchedules = scheduleRepository.findByYearAndNoWeek(currentYear, currentWeekNumber);
        //nếu chưa có
        if (WeekSchedules.isEmpty()) {
            // Tạo mới lịch học cho tuần hiện tại
            Schedule schedule = new Schedule();
            schedule.setYear(currentYear);
            schedule.setNoWeek(currentWeekNumber);
            for (int i = 2; i <= 7; i++){
                WeekDTO weekDTO = new WeekDTO();
                if (i == 2){
                    Date startOfWeek = currentWeek.getTime();
                    schedule.setStartWeek(startOfWeek);
                }
                if (i == 7){
                    Date endOfWeek = currentWeek.getTime();
                    schedule.setEndWeek(endOfWeek);
                }

                weekDTO.setDayOfMonthInDateFormat(currentWeek.getTime());
                weekDTO.setDayOfWeek(i);
                weekDTO.setWeekOfYear(currentWeekNumber);
                weekDTO.setYear(currentYear);
                currentWeek.add(Calendar.DAY_OF_MONTH,1);

                weekDTOS.add(weekDTO);
            }
            scheduleRepository.save(schedule);

        }// end if
        else {
            for (int i = 2; i <= 7; i++){
                WeekDTO weekDTO = new WeekDTO();
                weekDTO.setDayOfMonthInDateFormat(currentWeek.getTime());
                weekDTO.setDayOfWeek(i);
                weekDTO.setWeekOfYear(currentWeekNumber);
                weekDTO.setYear(currentYear);
                currentWeek.add(Calendar.DAY_OF_MONTH,1);

                weekDTOS.add(weekDTO);
            }

        }



        return weekDTOS;
    }


    @Override
    public List<Schedule> findByYearAndNoWeek(int year, int noWeek) {
        return scheduleRepository.findByYearAndNoWeek(year, noWeek);
    }

    public Schedule findScheduleByYearAndNoWeek(int year, int noWeek) {
        return scheduleRepository.findScheduleByYearAndNoWeek(year, noWeek);
    }
}
