package ru.netunix.aopspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private String name;
    private Integer amount;
}
