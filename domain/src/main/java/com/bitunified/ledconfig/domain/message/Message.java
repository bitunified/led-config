package com.bitunified.ledconfig.domain.message;

import java.util.List;

public class Message {
    public static final int ERROR = 0;
    public static final int WARNING = 1;

    private String message;

    private Integer status;

    public Message() {

    }

    public Message(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Message: " + this.message + " - Status: " + status;
    }
}
