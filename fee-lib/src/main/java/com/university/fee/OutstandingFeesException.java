package com.university.fee;

public class OutstandingFeesException extends RuntimeException {
    public OutstandingFeesException(String message) {
        super(message);
    }
}
