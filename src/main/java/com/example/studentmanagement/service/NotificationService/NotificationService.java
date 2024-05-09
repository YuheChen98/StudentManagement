package com.example.studentmanagement.service.NotificationService;

import com.example.studentmanagement.entity.Notification.Notification;
import org.springframework.stereotype.Service;

/**
 * Service interface for handling operations related to various request within the student management system.
 * author: Lingxu Huang
 * date: April 27th 2024
 */
@Service
public interface NotificationService {

    final String ABSENCE = "absence";
    final String MEETING = "meeting";
    final String COURSEWORKEXTENSION = "courseworkextension";
    final String SUSPENSION = "suspension";
    final String WITHDRAWN = "withdrawn";
    final String NORMAL = "normal";
    Notification sendNewRequest(Notification notification);

    Notification acceptRequest(Notification notification);

    Notification rejectRequest(Notification notification);
}
