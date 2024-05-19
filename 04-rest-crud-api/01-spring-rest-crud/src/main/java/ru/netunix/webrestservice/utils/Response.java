package ru.netunix.webrestservice.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private int status;
    private Object data;
    private String message;
}
