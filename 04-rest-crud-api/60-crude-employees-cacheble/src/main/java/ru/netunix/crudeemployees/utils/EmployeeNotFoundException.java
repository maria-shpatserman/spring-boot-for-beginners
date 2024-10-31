package ru.netunix.crudeemployees.utils;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
