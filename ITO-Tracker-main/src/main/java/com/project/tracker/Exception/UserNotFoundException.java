package com.project.tracker.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is an Exception class for UserNotFoundException.
 * @ResponseStatus is used to mark a method or an exception class with a status code and reason for that status should be returned.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

    private String errorCode;
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public UserNotFoundException(String errorCode, String errorMessage) {
        super("Error Code : " +errorCode + ", Error Message : " +errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public UserNotFoundException()
    {

    }
}
