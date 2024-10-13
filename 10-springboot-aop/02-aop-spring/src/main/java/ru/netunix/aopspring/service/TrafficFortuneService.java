package ru.netunix.aopspring.service;

public interface TrafficFortuneService {
    String getFortune();
    String getFortune(Boolean throwException);
}
