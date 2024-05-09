package com.example.studentmanagement.utils;


/**
 * Data Transfer Object (DTO) for handling login requests within the Student Management System.
 * This class is used to transfer user credentials data, including the user ID, password, and role,
 * during the authentication process.
 *
 * @author Yuhe Chen
 * date: May 9th 2024
 */
public class LoginRequest {

    private String userId;
    private String password;
    private String role;

    /**
     * Default constructor.
     */
    public LoginRequest() {}

    /**
     * Gets the user ID.
     *
     * @return The user ID.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the password.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the user.
     *
     * @return The user's role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role The role to set.
     */
    public void setRole(String role) {
        this.role = role;
    }
}
