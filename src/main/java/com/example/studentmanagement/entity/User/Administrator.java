package com.example.studentmanagement.entity.User;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * Represents an administrator in the student management system.
 */
public class Administrator {
    @TableId
    private String adminId;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    /**
     * Default constructor for Administrator.
     */
    public Administrator(){}

    /**
     * Gets the administrator's unique ID.
     * @return the unique ID of the administrator
     */
    public String getAdminId() {
        return adminId;
    }

    /**
     * Sets the administrator's unique ID.
     * @param adminId the unique ID to be set for the administrator
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    /**
     * Gets the administrator's password.
     * @return the password of the administrator
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the administrator's password.
     * @param password the password to be set for the administrator
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the administrator's first name.
     * @return the first name of the administrator
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the administrator's first name.
     * @param firstName the first name to be set for the administrator
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the administrator's last name.
     * @return the last name of the administrator
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the administrator's last name.
     * @param lastName the last name to be set for the administrator
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the administrator's email.
     * @return the email of the administrator
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the administrator's email.
     * @param email the email to be set for the administrator
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
