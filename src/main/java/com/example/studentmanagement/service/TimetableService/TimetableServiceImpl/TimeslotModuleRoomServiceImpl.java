package com.example.studentmanagement.service.TimetableService.TimetableServiceImpl;

import com.example.studentmanagement.entity.Programme.Module;
import com.example.studentmanagement.entity.Programme.Programme;
import com.example.studentmanagement.entity.Programme.ProgrammeModule;
import com.example.studentmanagement.entity.Timetable.TimeslotModuleRoom;
import com.example.studentmanagement.entity.User.Lecturer;
import com.example.studentmanagement.entity.User.Student;
import com.example.studentmanagement.mapper.ProgrammeMapper.ModuleMapper;
import com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeMapper;
import com.example.studentmanagement.mapper.ProgrammeMapper.ProgrammeModuleMapper;
import com.example.studentmanagement.mapper.TimetableMapper.TimeslotModuleRoomMapper;
import com.example.studentmanagement.mapper.UserMapper.LecturerMapper;
import com.example.studentmanagement.mapper.UserMapper.StudentMapper;
import com.example.studentmanagement.service.TimetableService.TimeslotModuleRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.LinkedList;
import java.util.List;

/**
 * Service class for handling operations related to timetable within the student management system.
 * author: Lingxu Huang
 * modified by Lingxu Huang at May 5th to add method for getting student and lecturer's timeslots
 * date: May 3rd 2024, mod May 5th
 */
@Service
public class TimeslotModuleRoomServiceImpl implements TimeslotModuleRoomService {


    @Autowired
    TimeslotModuleRoomMapper timeslotModuleRoomMapper;

    @Autowired
    ModuleMapper moduleMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    LecturerMapper lecturerMapper;

    /**
     * This method will create a new timeslot for a module, with the room that uses this timeslot, in the database
     * To success, this method will check the timeslot by:
     * check if the required timeslot overlap with timeslots of required room, and
     * check if the required timeslot overlap with timeslots of all modules within the same programme and same year of required module
     *
     * @param timeslotModuleRoom A TimeslotModuleRoom object containing the timeslot, moduleId and roomId
     * @return the TimeslotModuleRoom object that will be created, null if failed
     */
    public TimeslotModuleRoom createNewTimeslot(TimeslotModuleRoom timeslotModuleRoom){

        List<TimeslotModuleRoom> roomTimeslotList = timeslotModuleRoomMapper.SelectByRoomId(timeslotModuleRoom.getRoomId());
        List<Module> sameYearModuleList = moduleMapper.selectSameYearModuleByModuleId(timeslotModuleRoom.getModuleId());
        List<TimeslotModuleRoom> moduleTimeslotList = new LinkedList<>();

        if(!sameYearModuleList.isEmpty()){
            for(Module module: sameYearModuleList){
                List<TimeslotModuleRoom> timeslotModuleRoomList = timeslotModuleRoomMapper.SelectByModuleId(module.getModuleId());
                if(!timeslotModuleRoomList.isEmpty()){
                    moduleTimeslotList.addAll(timeslotModuleRoomList);
                    System.out.println(moduleTimeslotList+"\n");
                }
            }
        }


        if(!roomTimeslotList.isEmpty()){
            boolean timeOverlap ;
            for(TimeslotModuleRoom roomTimeslot : roomTimeslotList){
                LocalDateTime startDateTime1 = roomTimeslot.getStartDateTime();
                LocalDateTime endDateTime1 = roomTimeslot.getEndDateTime();
                LocalDateTime startDateTime2 = timeslotModuleRoom.getStartDateTime();
                LocalDateTime endDateTime2 = timeslotModuleRoom.getEndDateTime();
                timeOverlap = checkOverlap(startDateTime1,endDateTime1,startDateTime2,endDateTime2);

                if(timeOverlap){
                    System.out.println("Timeslot overlap with other room at\n"+ roomTimeslot
                    + "Your timeslot is\n" + timeslotModuleRoom);
                    return null;
                }
            }

        }

        if(!moduleTimeslotList.isEmpty()){
            boolean timeOverlap ;
            for(TimeslotModuleRoom moduleTimeslot : moduleTimeslotList){
                LocalDateTime startDateTime1 = moduleTimeslot.getStartDateTime();
                LocalDateTime endDateTime1 = moduleTimeslot.getEndDateTime();
                LocalDateTime startDateTime2 = timeslotModuleRoom.getStartDateTime();
                LocalDateTime endDateTime2 = timeslotModuleRoom.getEndDateTime();
                timeOverlap = checkOverlap(startDateTime1,endDateTime1,startDateTime2,endDateTime2);
                if(timeOverlap){
                    System.out.println("Timeslot overlap with other modules of the same programme at\n" + moduleTimeslot
                    + "Your timeslot is\n" + timeslotModuleRoom);
                    return null;
                }
            }

        }


        int i = timeslotModuleRoomMapper.add(timeslotModuleRoom);
        if(i >0){
            return timeslotModuleRoom;
        } else {
            System.out.println("failed.");
            return null;
        }

    }

    /**
     * This method will get timetable of a lecturer based on the student's Id
     * @param studentId the student's Id
     * @return A list of TimeslotModuleRoom object represent the student's timetable
     */
    public List<TimeslotModuleRoom> getTimetableByStudentId(String studentId){

        //Find the module initial letters by the student's grade
        Student student = studentMapper.selectByStudentId(studentId);
        Programme programme = student.getProgramme();
        String programmeIdInitial = programme.getProgrammeId().substring(0,2);
        String moduleIdGrade = calculateGrade(student.getStartTime());
        String moduleIdInitial = programmeIdInitial +
                moduleIdGrade;

        List<Module> sameYearModuleList = moduleMapper.selectSameYearModuleByModuleId(moduleIdInitial);

        List<TimeslotModuleRoom> studentTimeslotList = new LinkedList<>();
        for(Module module: sameYearModuleList){
            List<TimeslotModuleRoom> timeslotModuleRoomList = timeslotModuleRoomMapper.SelectByModuleId(module.getModuleId());
            if(timeslotModuleRoomList != null){
                studentTimeslotList.addAll(timeslotModuleRoomList);
            }
        }

        return studentTimeslotList;

    }

    /**
     * This method will get timetable of a lecturer based on the lecturer's Id
     * @param lecturerId the lecturer's Id
     * @return A list of TimeslotModuleRoom object represent the lecturer's timetable
     */
    public List<TimeslotModuleRoom> getTimetableByLecturerId(String lecturerId){

        Lecturer lecturer = lecturerMapper.selectById(lecturerId);

        System.out.println(lecturer.getModules());
        for(Module module:lecturer.getModules()){
            String moduleId = module.getModuleId();
            System.out.println(moduleId);
        }

        List<TimeslotModuleRoom> lecturerTimeslotList = new LinkedList<>();
        for(Module module: lecturer.getModules()){
            List<TimeslotModuleRoom> timeslotModuleRoomList = timeslotModuleRoomMapper.SelectByModuleId(module.getModuleId());
            if(timeslotModuleRoomList != null){
                lecturerTimeslotList.addAll(timeslotModuleRoomList);
            }
        }

        return lecturerTimeslotList;

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
