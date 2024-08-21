package com.example.fullCafe_spring_maven.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    private String message;
    private String description;
    private LocalDateTime timestamp;

    public ErrorDetails(String message, String description, LocalDateTime timestamp) {
        this.message = message;
        this.description = description;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "message='" + message + '\'' +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
