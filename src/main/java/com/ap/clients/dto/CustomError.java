package com.ap.clients.dto;

import java.time.Instant;

public class CustomError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;

    public CustomError() {
    }

    public CustomError(Instant timestamp, Integer status, String path, String error) {
        this.timestamp = timestamp;
        this.status = status;
        this.path = path;
        this.error = error;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
