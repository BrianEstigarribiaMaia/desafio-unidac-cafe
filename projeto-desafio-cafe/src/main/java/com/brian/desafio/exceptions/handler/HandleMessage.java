package com.brian.desafio.exceptions.handler;

import java.time.LocalDateTime;

public class HandleMessage {

    private final String message;
    private final LocalDateTime timestamp;

    public HandleMessage(String message) {
        this.message = message;
        timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
