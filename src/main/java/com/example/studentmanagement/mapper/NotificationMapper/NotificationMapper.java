package com.example.studentmanagement.mapper.NotificationMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanagement.entity.Notification.Notification;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper interface for the Notification entity, providing database interaction methods to manage notification records.
 * author: Lingxu Huang
 * modified by Lingxu Huang at May 4th 2024 to add method for retrieve various type of notification
 * date: April 29th 2024, mod May 4th 2024
 */
@Mapper
public interface NotificationMapper extends BaseMapper<Notification> {

    /**
     * Retrieves all types of notification of a user according to user ID
     * @param userid the unique identifier of the user
     * @return a list of notifications associated with the specified user
     */
    @Select("select * from notification where (student_id = #{userid} or tutor_id = #{userid} or admin_id = #{userid} or lecturer_id = #{userid})")
    List<Notification> selectAllNotificationByUserId(String userid);

    /**
     * Retrieves all notifications of a certain type of user, according to user ID and notification type
     * @param userid the unique identifier of user
     * @param notificationType the type of notification
     * @return a list of notifications associated with the specified user and notification type
     */
    @Select("select * from notification where (notification_type = #{notificationType} and (student_id = #{userid} or tutor_id = #{userid} or admin_id = #{userid} or lecturer_id = #{userid} ))")
    List<Notification> selectNotificationByUserIdAndType(String userid, String notificationType);

    /**
     * Retrieves all notifications of type absence,meeting,suspension,withdrawn of user, according to user ID
     * @param userid the unique identifier of user
     * @return a list of notifications associated with the specified user and notification type absence,meeting,suspension,withdrawn
     */
    @Select("select * from notification where (notification_type in ('absence','meeting','suspension','withdrawn') and (student_id = #{userid} or tutor_id = #{userid} or admin_id = #{userid} or lecturer_id = #{userid}))" )
    List<Notification> select_AMSW_NotificationByUserId(String userid);

    /**
     * Retrieves all notifications of type absence,meeting of user, according to user ID
     * @param userid the unique identifier of user
     * @return a list of notifications associated with the specified user and notification type absence,meeting
     */
    @Select("select * from notification where (notification_type in ('absence','meeting') and (student_id = #{userid} or tutor_id = #{userid} or admin_id = #{userid} or lecturer_id = #{userid}))")
    List<Notification> select_AM_NotificationByUserId(String userid);

    /**
     * Retrieves all notifications of type suspension,withdrawn of user, according to user ID
     * @param userid the unique identifier of user
     * @return a list of notifications associated with the specified user and notification type suspension,withdrawn
     */
    @Select("select * from notification where (notification_type in ('suspension','withdrawn') and (student_id = #{userid} or tutor_id = #{userid} or admin_id = #{userid} or lecturer_id = #{userid}))")
    List<Notification> select_SW_NotificationByUserId(String userid);

    /**
     * Retrieves all notifications of type absence of user, according to user ID
     * @param userid the unique identifier of user
     * @return a list of notifications associated with the specified user and notification type absence
     */
    @Select("select * from notification where (notification_type = 'absence' and (student_id = #{userid} or tutor_id = #{userid} or admin_id = #{userid} or lecturer_id = #{userid}))")
    List<Notification> selectAbsenceNotificationByUserId(String userid);

    /**
     * Retrieves all notifications of type meeting of user, according to user ID
     * @param userid the unique identifier of user
     * @return a list of notifications associated with the specified user and notification type meeting
     */
    @Select("select * from notification where (notification_type = 'meeting' and (student_id = #{userid} or tutor_id = #{userid} or admin_id = #{userid} or lecturer_id = #{userid}))")
    List<Notification> selectMeetingNotificationByUserId(String userid);


    /**
     * Retrieves all notifications of type courseworkextension of user, according to user ID
     * @param userid the unique identifier of user
     * @return a list of notifications associated with the specified user and notification type courseworkextension
     */
    @Select("select * from notification where (notification_type = 'courseworkextension' and (student_id = #{userid} or tutor_id = #{userid} or admin_id = #{userid} or lecturer_id = #{userid}))")
    List<Notification> selectCourseworkExtensionByUserId(String userid);

    /**
     * Retrieves all notifications of type suspension of user, according to user ID
     * @param userid the unique identifier of user
     * @return a list of notifications associated with the specified user and notification type suspension
     */
    @Select("select * from notification where (notification_type = 'suspension' and (student_id = #{userid} or tutor_id = #{userid} or admin_id = #{userid} or lecturer_id = #{userid}))")
    List<Notification> selectSuspensionNotificationByUserId(String userid);

    /**
     * Retrieves all notifications of type withdrawn of user, according to user ID
     * @param userid the unique identifier of user
     * @return a list of notifications associated with the specified user and notification type withdrawn
     */
    @Select("select * from notification where (notification_type = 'withdrawn' and (student_id = #{userid} or tutor_id = #{userid} or admin_id = #{userid} or lecturer_id = #{userid}))")
    List<Notification> selectWithdrawnNotificationByUserId(String userid);

    /**
     * Retrieves all notifications of type normal of user, according to user ID
     * @param userid the unique identifier of user
     * @return a list of notifications associated with the specified user and notification type normal
     */
    @Select("select * from notification where (notification_type = 'normal' and (student_id = #{userid} or tutor_id = #{userid} or admin_id = #{userid} or lecturer_id = #{userid} ))")
    List<Notification> selectNormalNotificationById(String userid);

    /**
     * Retrieves a specific notification by its notification ID
     * @param notificationId the unique identifier of a notification
     * @return a list of notifications associated with the specified notification ID
     */
    @Select(("select * from notification where (notification_id = #{notificationId})"))
    List<Notification> selectNotificationByNotificationId(int notificationId);

    /**
     * Inserts a new notification record into the database.
     * @param notification The Notification entity to be added.
     * @return The number of rows affected by the insert operation.
     */
    @Insert("insert into notification values (#{notificationId},#{sendTime},#{senderId},#{receiverId},#{content},#{isAccepted},#{isRejected},#{notificationType},#{adminId},#{studentId},#{tutorId},#{lecturerId},#{examCourseworkId},#{moduleId},#{programmeId},#{absenceDate},#{meetingStartDateTime},#{meetingEndDateTime},#{meetingRoomId})")
    int add(Notification notification);


}
