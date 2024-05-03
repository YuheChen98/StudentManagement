package com.example.studentmanagement.entity.User;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.studentmanagement.entity.Programme.Module;

import java.util.List;

/**
 * Represents a lecturer within the student management system.
 * This class includes details about the lecturer and their associated teaching modules.
 */
public class Lecturer {
    @TableId
    private String lecturerId;
    private String information;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    @TableField(exist = false)
    private List<Module> modules;

    /**
     * Default constructor.
     */
    public Lecturer() {}

    /**
     * Gets the unique identifier of the lecturer.
     * @return the lecturer's ID
     */
    public String getLecturerId() {
        return lecturerId;
    }

    /**
     * Sets the unique identifier for the lecturer.
     * @param lecturerId the new ID for the lecturer
     */
    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    /**
     * Gets additional information about the lecturer.
     * @return a string containing information about the lecturer
     */
    public String getInformation() {
        return information;
    }

    /**
     * Sets additional information about the lecturer.
     * @param information the information to set
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * Gets the password for the lecturer's account.
     * @return the lecturer's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the lecturer's account.
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the first name of the lecturer.
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the lecturer.
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the lecturer.
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the lecturer.
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email address of the lecturer.
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the lecturer.
     * @param email the new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the list of modules taught by the lecturer.
     * @return a list of modules
     */
    public List<Module> getModules() {
        return modules;
    }

    /**
     * Sets the list of modules taught by the lecturer.
     * @param modules the list of modules to be set
     */
    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
