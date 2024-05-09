package com.example.studentmanagement.service.TimetableService.TimetableServiceImpl;

import com.example.studentmanagement.entity.Programme.Module;
import com.example.studentmanagement.entity.Programme.Programme;
import com.example.studentmanagement.entity.Timetable.TimeslotModuleRoom;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper;
import com.example.studentmanagement.mapper.ProgrammeMapper.StudentExamMapper;
import com.example.studentmanagement.mapper.TimetableMapper.CalendarMapper;
import com.example.studentmanagement.mapper.TimetableMapper.TimeslotModuleRoomMapper;
import com.example.studentmanagement.mapper.UserMapper.StudentMapper;
import com.example.studentmanagement.service.TimetableService.CalendarService;
import com.example.studentmanagement.entity.Timetable.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.LinkedList;
import java.util.List;

/**
 * Service class for handling operations related to meetings between student and tutor within the student management system.
 * author: Lingxu Huang
 * modified by Lingxu Huang at May 5th 2024 to query all timeslots used by modules that belongs to the same programme
 * and same academic year of a student
 * date: May 3rd 2024, mod May 5th 2024
 */
@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    CalendarMapper calendarMapper;

    @Autowired
    ModuleMapper moduleMapper;

    @Autowired
    TimeslotModuleRoomMapper timeslotModuleRoomMapper;

    @Autowired
    StudentMapper studentMapper;

    /**
     * This method will create a new meeting for a student and a tutor, with the room that uses the timeslot of this meeting, in the database
     * To success, this method will check the timeslot by:
     * check if the required timeslot overlap with timeslots of required room, and
     * check if the required timeslot overlap with timeslots of all modules within the same programme and same grade of required student
     * check if the required timeslot overlap with timeslots of the required student himself
     * check if the required timeslot overlap with timeslots of the required tutor
     *
     * @param calendar A Calendar object containing the timeslot, studentId, tutorId and roomId
     * @return the Calendar object that will be created, null if failed
     */
    public Calendar createNewMeeting(Calendar calendar){

        List<Calendar> roomTimeslotList = calendarMapper.selectByRoomId(calendar.getRoomId());

        //Find the module initial letters by the student's grade
        Student student = studentMapper.selectByStudentId(calendar.getStudentId());
        Programme programme = student.getProgramme();
        int index = programme.getProgrammeId().indexOf("-");
        String programmeIdInitial = programme.getProgrammeId().substring(0,index);
        String moduleIdGrade = calculateGrade(student.getStartTime());
        System.out.println(moduleIdGrade);
        String moduleIdInitial = programmeIdInitial +
                moduleIdGrade;
        System.out.println(moduleIdInitial);


        List<Module> sameYearModuleList = moduleMapper.selectSameYearModuleByModuleId(moduleIdInitial);

        List<TimeslotModuleRoom> moduleTimeslotList = new LinkedList<>();

        List<Calendar> sameStudentCalendarList = calendarMapper.selectByStudentId(calendar.getStudentId());

        List<Calendar> sameTutorCalendarList = calendarMapper.selectByTutorId(calendar.getTutorId());

        if(!sameYearModuleList.isEmpty()){
            for(Module module: sameYearModuleList){
                List<TimeslotModuleRoom> timeslotModuleRoomList = timeslotModuleRoomMapper.SelectByModuleId(module.getModuleId());
                if(timeslotModuleRoomList != null){
                    moduleTimeslotList.addAll(timeslotModuleRoomList);
                    System.out.println(moduleTimeslotList+"\n");
                }
            }
        }

        if(!roomTimeslotList.isEmpty()){
            boolean timeOverlap;
            for(Calendar roomCalendar : roomTimeslotList){
                LocalDateTime startDateTime1 = roomCalendar.getStartDateTime();
                LocalDateTime endDateTime1 = roomCalendar.getEndDateTime();
                LocalDateTime startDateTime2 = calendar.getStartDateTime();
                LocalDateTime endDateTime2 = calendar.getEndDateTime();
                timeOverlap = checkOverlap(startDateTime1,endDateTime1,startDateTime2,endDateTime2);

                if(timeOverlap){
                    System.out.println("Timeslot overlap with other room at\n"+ roomCalendar
                            + "Your timeslot is\n" + calendar);
                    return null;
                }
            }

        }

        if(!moduleTimeslotList.isEmpty()){
            boolean timeOverlap;
            for(TimeslotModuleRoom moduleTimeslot : moduleTimeslotList){
                LocalDateTime startDateTime1 = moduleTimeslot.getStartDateTime();
                LocalDateTime endDateTime1 = moduleTimeslot.getEndDateTime();
                LocalDateTime startDateTime2 = calendar.getStartDateTime();
                LocalDateTime endDateTime2 = calendar.getEndDateTime();
                timeOverlap = checkOverlap(startDateTime1,endDateTime1,startDateTime2,endDateTime2);
                if(timeOverlap){
                    System.out.println("Timeslot overlap with other same year modules of the same programme at\n" + moduleTimeslot
                            + "Your timeslot is\n" + calendar);
                    return null;
                }
            }

        }

        if(!sameStudentCalendarList.isEmpty()){
            boolean timeOverlap;
            for(Calendar studentCalendar: sameStudentCalendarList){
                LocalDateTime startDateTime1 = studentCalendar.getStartDateTime();
                LocalDateTime endDateTime1 = studentCalendar.getEndDateTime();
                LocalDateTime startDateTime2 = calendar.getStartDateTime();
                LocalDateTime endDateTime2 = calendar.getEndDateTime();
                timeOverlap = checkOverlap(startDateTime1,endDateTime1,startDateTime2,endDateTime2);
                if(timeOverlap){
                    System.out.println("Timeslot overlap with the student you choose at\n" + studentCalendar
                            + "Your timeslot is\n" + calendar);
                    return null;
                }
            }
        }

        if(!sameTutorCalendarList.isEmpty()){
            boolean timeOverlap;
            for(Calendar tutorCalendar: sameTutorCalendarList){
                LocalDateTime startDateTime1 = tutorCalendar.getStartDateTime();
                LocalDateTime endDateTime1 = tutorCalendar.getEndDateTime();
                LocalDateTime startDateTime2 = calendar.getStartDateTime();
                LocalDateTime endDateTime2 = calendar.getEndDateTime();
                timeOverlap = checkOverlap(startDateTime1,endDateTime1,startDateTime2,endDateTime2);
                if(timeOverlap){
                    System.out.println("Timeslot overlap with the tutor you choose at\n" + tutorCalendar
                    + "Your timeslot is\n" + calendar);
                    return null;
                }
            }
        }

        int i = calendarMapper.add(calendar);
        if(i >0){
            return calendar;
        } else {
            System.out.println("failed.");
            return null;
        }

    }

    /**
     * This method will calculate the grade of a student according to student's startTime of programme
     * @param startTime the startTime of programme of the student
     * @return A String represent the grade of a student
     */
    private String calculateGrade(LocalDate startTime){
        LocalDate currentTime = LocalDate.now();

        Period period = Period.between(startTime,currentTime);
        int studentGrade = period.getYears() + 1;
        return "-Y" + studentGrade + "-";
    }

    /**
     * This method will check whether the two timeslot is overlap
     * @param start1 start time of the first timeslot
     * @param end1 end time of the first timeslot
     * @param start2 start time of the second timeslot
     * @param end2 end time of the second timeslot
     * @return true if overlap, false if not overlap
     */
    private boolean checkOverlap(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2){

        return (start1.isBefore(end2) || start1.isEqual(start2)) &&
                ((end1.isAfter(start2)) || end1.isEqual(end2));
    }
}
