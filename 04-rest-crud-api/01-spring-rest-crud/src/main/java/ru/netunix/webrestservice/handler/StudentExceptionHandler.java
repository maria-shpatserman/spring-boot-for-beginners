package ru.netunix.webrestservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netunix.webrestservice.utils.StudentErrorResponse;
import ru.netunix.webrestservice.utils.StudentNotFoundException;

@ControllerAdvice
public class StudentExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exception){
        ;
        return new ResponseEntity<>(StudentErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timeStamp(System.currentTimeMillis()).build(),HttpStatus.NOT_FOUND);

    }
    //add new exception handler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleAllExceptions(Exception exception){
        return new ResponseEntity<>(StudentErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(System.currentTimeMillis()).build(),HttpStatus.BAD_REQUEST);

    }
}
