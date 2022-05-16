package com.project.tracker.Exception;

import java.util.Date;

/**
 * This is an Exception class for all the error handling and exception handling.
 * Here, we have generated the getters and setters of all the fields in ErrorDetails and a constructor.
 */
public class ErrorDetails {
    private Date timeStamp;
    private String message;
    private String details;

    public ErrorDetails(Date timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
