package ru.netunix.webrestservice.utils;

import org.springframework.stereotype.Service;

@Service
public class AppUtils {
    public static String getCurrentMethodName() {
        return StackWalker.getInstance()
                .walk(s -> s.skip(1).findFirst())
                .get()
                .getMethodName();
    }

    public static String getCallerMethodName() {
        return StackWalker.getInstance()
                .walk(s -> s.skip(2).findFirst())
                .get()
                .getMethodName();
    }
}
