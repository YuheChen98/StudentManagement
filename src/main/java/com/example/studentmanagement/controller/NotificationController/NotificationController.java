package com.example.studentmanagement.controller.NotificationController;

import com.example.studentmanagement.entity.Notification.Notification;
import com.example.studentmanagement.mapper.NotificationMapper.NotificationMapper;
import com.example.studentmanagement.service.NotificationService.NotificationService;
import com.example.studentmanagement.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * REST controller for managing notifications(including various request and normal notification) within the student management system.
 * Provides endpoints for students to send request, tutors to accept or reject requests,
 * and for all users to retrieve various type of notifications.
 * author: Lingxu Huang
 * modified by Lingxu Huang at May 3rd to cover getting current user function
 * modified by Lingxu Huang at May 4th to add method for retrieve various type of notification
 * modified by Lingxu Huang at May 5th to use Result class as return for sending, accepting and rejecting method
 * modified by Lingxu Huang at May 7th to use correct mapping annotation and correct url style
 * date: April 27th 2024, mod May 3rd, mod May 4th, mod May 5th, mod May 7th
 */
@RestController
public class NotificationController {

    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private NotificationService notificationService;

    /**
     * Retrieves the currently authenticated user's ID from the security context.
     * @return The ID of the currently authenticated user.
     */
    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }


    /**
     * Retrieves all types of notification of a user according to current user ID
     * @return a list of notifications associated with the current user
     */
    @Operation(summary = "Get all types of notifications according to current user Id")
    @GetMapping("/notification/all")
    public List<Notification> getAllNotificationById(){
        String userId = getCurrentUserId();
        return notificationMapper.selectAllNotificationByUserId(userId);
    }

    /**
     * Retrieves all notifications of type absence,meeting,suspension,withdrawn of user, according to current user ID
     * @return a list of notifications associated with the current user and notification type of absence,meeting,suspension,withdrawn
     */
    @Operation(summary = "Get notification types of absence,meeting,suspension,withdrawn according to current user Id")
    @GetMapping("/notification/amsw")
    public List<Notification> get_AMSW_NotificationById(){
        String userId = getCurrentUserId();
        return notificationMapper.select_AMSW_NotificationByUserId(userId);
    }

    /**
     * Retrieves all notifications of type absence,meeting of user, according to current user ID
     * @return a list of notifications associated with the current user and notification type of absence,meeting
     */
    @Operation(summary = "Get notification types of absence,meeting according to current user Id")
    @GetMapping("/notification/am")
    public List<Notification> get_AM_NotificationById(){
        String userId = getCurrentUserId();
        return notificationMapper.select_AM_NotificationByUserId(userId);
    }

    /**
     * Retrieves all notifications of type suspension,withdrawn of user, according to current user ID
     * @return a list of notifications associated with the current user and notification type of suspension,withdrawn
     */
    @Operation(summary = "Get notification types of suspension,withdrawn according to current user Id")
    @GetMapping("/notification/rsw")
    public List<Notification> get_SW_NotificationById(){
        String userId = getCurrentUserId();
        return notificationMapper.select_SW_NotificationByUserId(userId);
    }

    /**
     * Retrieves all notifications of type absence of user, according to current user ID
     * @return a list of notifications associated with the current user and notification type of absence
     */
    @Operation(summary = "Get notification types of absence according to current user Id")
    @GetMapping("/notification/absence")
    public List<Notification> getAbsenceNotificationById(){
        String userId = getCurrentUserId();
        return notificationMapper.selectAbsenceNotificationByUserId(userId);
    }

    /**
     * Retrieves all notifications of type meeting of user, according to current user ID
     * @return a list of notifications associated with the current user and notification type of meeting
     */
    @Operation(summary = "Get notification types of meeting according to current user Id")
    @GetMapping("/notification/meeting")
    public List<Notification> getMeetingNotificationById(){
        String userId = getCurrentUserId();
        return notificationMapper.selectMeetingNotificationByUserId(userId);
    }

    /**
     * Retrieves all notifications of type courseworkextension of user, according to current user ID
     * @return a list of notifications associated with the current user and notification type of courseworkextension
     */
    @Operation(summary = "Get notification types of courseworkextension according to current user Id")
    @GetMapping("/notification/courseworkExtension")
    public List<Notification> getCourseworkExtensionById(){
        String userId = getCurrentUserId();
        return notificationMapper.selectCourseworkExtensionByUserId(userId);
    }

    /**
     * Retrieves all notifications of type suspension of user, according to current user ID
     * @return a list of notifications associated with the current user and notification type of suspension
     */
    @Operation(summary = "Get notification types of suspension according to current user Id")
    @GetMapping("/notification/suspension")
    public List<Notification> getSuspensionNotificationById(){
        String userId = getCurrentUserId();
        return notificationMapper.selectSuspensionNotificationByUserId(userId);
    }

    /**
     * Retrieves all notifications of type withdrawn of user, according to current user ID
     * @return a list of notifications associated with the current user and notification type of withdrawn
     */
    @Operation(summary = "Get notification types of withdrawn according to current user Id")
    @GetMapping("/notification/withdrawn")
    public List<Notification> getWithdrawnNotificationById(){
        String userId = getCurrentUserId();
        return notificationMapper.selectWithdrawnNotificationByUserId(userId);
    }

    /**
     * Retrieves all notifications of type normal of user, according to current user ID
     * @return a list of notifications associated with the current user and notification of type normal
     */
    @Operation(summary = "Get normal notifications according to current user Id")
    @GetMapping("/notification/normal")
    public List<Notification> getNormalNotificationById(){
        String userId = getCurrentUserId();
        return notificationMapper.selectNormalNotificationById(userId);
    }

    /**
     * Send a notification of a certain type as a request to certain user.
     * @param notification the notification object to be sent
     * @return A Result object containing the created notification in the database or an error message.
     */
    @Operation(summary = "Send a request")
    @PostMapping("/notification/sendRequest")
    @PreAuthorize("hasRole('STUDENT')")
    public Result sendRequest(@RequestBody Notification notification){
        String studentId = getCurrentUserId();
        notification.setSenderId(studentId);
        notification.setSendTime(LocalDateTime.now());
        Notification checkSuccess = notificationService.sendNewRequest(notification);
        if(checkSuccess != null){
            return Result.ok().data("request",checkSuccess);
        }else {
            return Result.error().message("send request failed");
        }
    }

    /**
     * Accept various type of request based on the notification ID
     * @param notification the Notification object including the ID of notification that will be accepted
     * @return A Result object containing the accepted notification in the database or an error message.
     */
    @Operation(summary = "Accept a request, should provide notification Id")
    @PutMapping("notification/acceptRequest")
    @PreAuthorize("hasAnyRole('ADMIN', 'TUTOR')")
    public Result acceptRequest(@RequestBody Notification notification){
        Notification checkSuccess = notificationService.acceptRequest(notification);
        if(checkSuccess != null){
            return Result.ok().data("request",checkSuccess);
        }else {
            return Result.error().message("accept request failed");
        }
    }

    /**
     * Reject various type of request based on the notification ID
     * @param notification the Notification object including the ID of notification that will be rejected
     * @return A Result object containing the rejected notification in the database or an error message.
     */
    @Operation(summary = "Reject a request, should provide notification Id")
    @PutMapping("notification/rejectRequest")
    @PreAuthorize("hasAnyRole('ADMIN', 'TUTOR')")
    public Result rejectRequest(@RequestBody Notification notification){
        Notification checkSuccess = notificationService.rejectRequest(notification);
        if(checkSuccess != null){
            return Result.ok().data("request",checkSuccess);
        }else {
            return Result.error().message("reject request failed");
        }
    }



}
