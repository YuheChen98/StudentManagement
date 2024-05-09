package com.example.studentmanagement.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * Utility class for building uniform response objects for API interactions.
 * Provides methods to construct responses with a status, message, and data fields.
 * It supports fluent API style for building response objects.
 *
 * Usage examples:
 * - Result.ok().message("Query successful").data("items", itemList);
 * - Result.error().code(404).message("Item not found");
 *
 * @author Yuhe Chen
 * date: May 9th 2024
 */
public class Result {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    private Result() {
    }

    /**
     * Creates a success result with default values.
     *
     * @return A success result instance.
     */
    public static Result ok() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("Successful");
        return r;
    }

    /**
     * Creates an error result with default values.
     *
     * @return An error result instance.
     */
    public static Result error() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("Error");
        return r;
    }

    /**
     * Returns the success status of this result.
     *
     * @return true if the operation was successful, false otherwise.
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * Sets the success status of this result.
     *
     * @param success the success status to set, true for success and false for failure
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * Returns the code of this result.
     *
     * @return the status code associated with this result.
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Sets the status code of this result.
     *
     * @param code the status code to set.
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * Returns the message of this result.
     *
     * @return the message describing the result.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message describing this result.
     *
     * @param message the message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the data map of this result.
     *
     * @return a map containing the data included in this result.
     */
    public Map<String, Object> getData() {
        return data;
    }

    /**
     * Sets the data map for this result.
     *
     * @param data the map of data to set.
     */
    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    /**
     * Sets the success status of this result and allows for chaining of other configuration methods.
     *
     * @param success the success status to set, true for success and false for failure
     * @return the current instance of {@code Result} for method chaining
     */
    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    /**
     * Sets the message describing this result and allows for chaining of other configuration methods.
     *
     * @param message the message to set
     * @return the current instance of {@code Result} for method chaining
     */
    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * Sets the status code of this result and allows for chaining of other configuration methods.
     *
     * @param code the status code to set
     * @return the current instance of {@code Result} for method chaining
     */
    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    /**
     * Adds a single data entry to the result's data map and allows for chaining of other configuration methods.
     *
     * @param key   the key under which the data is stored
     * @param value the data value to store
     * @return the current instance of {@code Result} for method chaining
     */
    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    /**
     * Sets the entire data map for the result and allows for chaining of other configuration methods.
     *
     * @param map a map containing all data entries to be included in the result
     * @return the current instance of {@code Result} for method chaining
     */
    public Result data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}