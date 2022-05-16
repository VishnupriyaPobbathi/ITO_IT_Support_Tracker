package com.project.tracker.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


/**
 * This is an Exception class for all the error handling and exception handling.
 * To handle these exceptions, we need to create a method in a @ControllerAdvice class and annotate the sub methods with @ExceptionHandler.
 * @ControllerAdvice allows to handle exceptions across the whole application by integrating the exceptions into a single component.
 * @ExceptionHandler is an annotation used to handle exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * This method contains ResourceNotFoundException.
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request)
    {
        ErrorDetails e1 = new ErrorDetails(new Date(),ex.getErrorMessage(), request.getDescription(false));
        return new ResponseEntity<>(e1, HttpStatus.NOT_FOUND);
    }

    /**
     * This method contains UserNotFoundException.
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex, WebRequest request)
    {
        ErrorDetails e1 = new ErrorDetails(new Date(),ex.getErrorMessage(), request.getDescription(false)+"?id=" +request.getParameter("id"));
        return new ResponseEntity<>(e1, HttpStatus.NOT_FOUND);
    }
}
