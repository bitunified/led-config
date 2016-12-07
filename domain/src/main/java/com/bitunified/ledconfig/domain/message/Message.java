package com.bitunified.ledconfig.domain.message;


public class Message {
    private Integer step=null;

    private String message;

    private MessageStatus status=MessageStatus.ERROR;

    public Message() {

    }
    public Message(String message, Integer step, MessageStatus status) {
        this.message = message;
        this.status = status;
        this.step=step;
    }
    public Message(String message, MessageStatus status) {
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

    public MessageStatus getStatus() {
        return this.status;
    }

    public void setStatus(final MessageStatus status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Message: " + this.message + " - Status: " + status;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}
