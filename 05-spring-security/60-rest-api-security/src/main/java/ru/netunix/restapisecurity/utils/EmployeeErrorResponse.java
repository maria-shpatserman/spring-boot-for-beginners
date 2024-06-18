package ru.netunix.restapisecurity.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EmployeeErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}
