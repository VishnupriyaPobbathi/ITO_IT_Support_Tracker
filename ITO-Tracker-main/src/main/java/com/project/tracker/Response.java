package com.project.tracker;

/**
 * This is Response class which contains getters , setters and constructors.
 */
public class Response {
    String message;

    public Response(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
