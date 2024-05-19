package ru.netunix.crudeemployees.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.netunix.crudeemployees.utils.EmployeeErrorResponse;
import ru.netunix.crudeemployees.utils.EmployeeNotFoundException;

@ControllerAdvice
public class EmployeeExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException exception) {
        ;
        return new ResponseEntity<>(EmployeeErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timeStamp(System.currentTimeMillis()).build(), HttpStatus.NOT_FOUND);

    }

    //add new exception handler
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleAllExceptions(Exception exception) {
        return new ResponseEntity<>(EmployeeErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(System.currentTimeMillis()).build(), HttpStatus.BAD_REQUEST);

    }
}
