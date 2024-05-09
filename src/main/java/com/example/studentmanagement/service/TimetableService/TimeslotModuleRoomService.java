package com.example.studentmanagement.service.TimetableService;

import com.example.studentmanagement.entity.Timetable.TimeslotModuleRoom;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service interface for handling operations related to timetable within the student management system.
 * author: Lingxu Huang
 * date: May 3rd 2024
 */
@Service
public interface TimeslotModuleRoomService {

    TimeslotModuleRoom createNewTimeslot(TimeslotModuleRoom timeslotModuleRoom);

    List<TimeslotModuleRoom> getTimetableByStudentId(String studentId);

    List<TimeslotModuleRoom> getTimetableByLecturerId(String lecturerId);




}
