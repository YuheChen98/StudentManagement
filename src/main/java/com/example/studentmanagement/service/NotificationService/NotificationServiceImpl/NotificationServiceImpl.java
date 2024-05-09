package com.example.studentmanagement.service.NotificationService.NotificationServiceImpl;

import com.example.studentmanagement.controller.NotificationController.NotificationController;
import com.example.studentmanagement.entity.Notification.Notification;
import com.example.studentmanagement.entity.Timetable.Calendar;
import com.example.studentmanagement.mapper.NotificationMapper.NotificationMapper;
import com.example.studentmanagement.mapper.UserMapper.StudentMapper;
import com.example.studentmanagement.service.NotificationService.NotificationService;
import com.example.studentmanagement.service.TimetableService.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class for handling operations related to various request within the student management system.
 * author: Lingxu Huang
 * modified by Lingxu Huang at May 4th to introduce calendarService class for automatically creating new calendar
 * when accepting request
 * date: April 27th 2024, mod May 4th
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    CalendarService calendarService;


    /**
     * This method will send a notification of a certain type as a request to certain user.
     * @param notification the notification object including various information related to a request.
     * @return the Notification object that will be sent, null if failed
     */
    public Notification sendNewRequest(Notification notification) {

        //check the receiver first, a request could only be sent to tutor or administrator.
        String firstTwoChar = notification.getReceiverId().substring(0, 2);
        if (firstTwoChar.equals("PT")) {
            notification.setTutorId(notification.getReceiverId());
        } else if (firstTwoChar.equals(("AD"))) {
            notification.setAdminId(notification.getReceiverId());
        } else {
            System.out.println("Could only send request to Tutor or Administrator.");
            return null;
        }

        notification.setStudentId(notification.getSenderId());


        //check the type of request.
        List<Notification> NotificationList = notificationMapper.selectNotificationByUserIdAndType
                (notification.getSenderId(), notification.getNotificationType());
        if (notification.getNotificationType().equals(ABSENCE)) {
            //check if the date of absence has been requested.
            if (!NotificationList.isEmpty()) {
                boolean dateRequested = false;
                for (Notification notification1 : NotificationList) {
                    if (notification1.getAbsenceDate().equals(notification.getAbsenceDate())) {
                        dateRequested = true;
                    }
                }
                if (dateRequested) {
                    System.out.println("Absence date requested.");
                    return null;
                }
            }
        } else if (notification.getNotificationType().equals(MEETING)) {
            //check if the timeslot of meeting is already requested by the student
            if (!NotificationList.isEmpty()) {
                boolean timeOverlap = false;
                for(Notification notification1 : NotificationList){
                    LocalDateTime startDateTime1 = notification1.getMeetingStartDateTime();
                    LocalDateTime endDateTime1 = notification1.getMeetingEndDateTime();
                    LocalDateTime startDateTime2 = notification.getMeetingStartDateTime();
                    LocalDateTime endDateTime2 = notification.getMeetingEndDateTime();
                    timeOverlap = checkOverlap(startDateTime1,endDateTime1,startDateTime2,endDateTime2);

                    }
                if(timeOverlap){
                    System.out.println("Meeting time overlap.");
                    return null;
                }

            }
        } else if (notification.getNotificationType().equals(COURSEWORKEXTENSION)) {
            //A student can't send a coursework extension request for the same coursework twice.
            if(!NotificationList.isEmpty()){
                boolean sameCourseworkId = false;
                for(Notification notification1 : NotificationList){
                    if(notification1.getExamCourseworkId().equals(notification.getExamCourseworkId())){
                        sameCourseworkId = true;
                    }
                }
                if(sameCourseworkId){
                    System.out.println("Coursework Id requested.");
                    return null;
                }
            }
        } else if (notification.getNotificationType().equals(SUSPENSION)) {
            //A student can't send a suspension request twice.
            if (!NotificationList.isEmpty()) {
                System.out.println("This student has send suspension request.");
                return null;
            }

        } else if (notification.getNotificationType().equals(WITHDRAWN)) {
            //A student can't send a withdrawn request twice.
            if (!NotificationList.isEmpty()) {
                System.out.println("This student has send withdrawn request.");
                return null;
            }
        } else {
            return null;
        }



        int i = notificationMapper.add(notification);
        if (i > 0) {
            return notification;
        } else {
            return null;
        }


    }

    /**
     * This method is for accepting various type of request.
     * @param notification the notification object that represent a certain request
     * @return a Notification object of type normal, to inform sender that the request has been accepted, null if failed
     */
    public Notification acceptRequest(Notification notification) {


        List<Notification> requestedRequestList= notificationMapper.selectNotificationByNotificationId(notification.getNotificationId());

        Notification requestedRequest;
        if(requestedRequestList != null){
            requestedRequest = requestedRequestList.get(0);
        }else {
            System.out.println("This request is already handled.");
            return null;
        }

        //If the request is already accepted or rejected, it can't be accepted or accepted twice.
        if(requestedRequest.getIsAccepted() || requestedRequest.getIsRejected()){
            return null;
        }

        requestedRequest.setIsAccepted(true);

        Notification normalNotification = new Notification();
        normalNotification.setNotificationType(NORMAL);
        normalNotification.setSendTime(LocalDateTime.now());
        normalNotification.setSenderId(requestedRequest.getReceiverId());
        normalNotification.setReceiverId(requestedRequest.getSenderId());
        normalNotification.setStudentId(requestedRequest.getStudentId());


        if (requestedRequest.getNotificationType().equals(ABSENCE)) {
            normalNotification.setContent("Your absence request has been accepted.");

        } else if (requestedRequest.getNotificationType().equals(MEETING)) {
            normalNotification.setContent("Your meeting request has been accepted.");
            Calendar calendar = new Calendar();
            calendar.setStartDateTime(requestedRequest.getMeetingStartDateTime());
            calendar.setEndDateTime(requestedRequest.getMeetingEndDateTime());
            calendar.setStudentId(requestedRequest.getStudentId());
            calendar.setTutorId(requestedRequest.getTutorId());
            calendar.setRoomId(requestedRequest.getMeetingRoomId());
            Calendar checkSuccessful = calendarService.createNewMeeting(calendar);
            if(checkSuccessful == null){
                return null;
            }

        } else if (requestedRequest.getNotificationType().equals(COURSEWORKEXTENSION)) {
            normalNotification.setContent("Your coursework extension request has been accepted.");

        } else if (requestedRequest.getNotificationType().equals(SUSPENSION)) {
            normalNotification.setContent("Your programme suspension request has been accepted.");

        } else if (requestedRequest.getNotificationType().equals(WITHDRAWN)) {
            normalNotification.setContent("Your programme withdraw request has been accepted.");

        }else {
            return null;
        }

        notificationMapper.updateById(requestedRequest);

        int j = notificationMapper.add(normalNotification);
        if (j > 0) {
            return normalNotification;
        } else {
            return null;
        }


    }

    /**
     * This method is for rejecting various type of request.
     * @param notification the notification object that represent a certain request
     * @return a Notification object of type normal, to inform sender that the request has been rejected, null if failed
     */
    public Notification rejectRequest(Notification notification) {

        List<Notification> requestedRequestList= notificationMapper.selectNotificationByNotificationId(notification.getNotificationId());

        Notification requestedRequest;
        if(requestedRequestList != null){
            requestedRequest = requestedRequestList.get(0);
        }else {
            return null;
        }

        //If the request is already accepted or rejected, it can't be rejected or rejected twice.
        if(requestedRequest.getIsAccepted() || requestedRequest.getIsRejected()){
            System.out.println("This request is already handled.");
            return null;
        }

        requestedRequest.setIsRejected(true);

        Notification normalNotification = new Notification();
        normalNotification.setNotificationType(NORMAL);
        normalNotification.setSendTime(LocalDateTime.now());
        normalNotification.setSenderId(requestedRequest.getReceiverId());
        normalNotification.setReceiverId(requestedRequest.getSenderId());
        normalNotification.setStudentId(requestedRequest.getStudentId());



        if (requestedRequest.getNotificationType().equals(ABSENCE)) {
            normalNotification.setContent("Your absence request has been rejected.");

        } else if (requestedRequest.getNotificationType().equals(MEETING)) {
            normalNotification.setContent("Your meeting request has been rejected.");

        } else if (requestedRequest.getNotificationType().equals(COURSEWORKEXTENSION)) {
            normalNotification.setContent("Your coursework extension request has been rejected.");

        } else if (requestedRequest.getNotificationType().equals(SUSPENSION)) {
            normalNotification.setContent("Your programme suspension request has been rejected.");

        } else if (requestedRequest.getNotificationType().equals(WITHDRAWN)) {
            normalNotification.setContent("Your programme withdraw request has been rejected.");

        } else {
            return null;
        }

        notificationMapper.updateById(requestedRequest);

        int j = notificationMapper.add(normalNotification);
        if (j > 0) {
            return normalNotification;
        } else {
            return null;
        }

    }

    /**
     * This method will check whether the two timeslot is overlap
     * @param start1 start time of the first timeslot
     * @param end1 end time of the first timeslot
     * @param start2 start time of the second timeslot
     * @param end2 end time of the second timeslot
     * @return true if overlap, false if not overlap
     */
    private boolean checkOverlap(LocalDateTime start1,LocalDateTime end1, LocalDateTime start2, LocalDateTime end2){

        return (start1.isBefore(end2) || start1.isEqual(start2))&&
                ((end1.isAfter(start2)) || end1.isEqual(end2));
    }
}


