package com.bitunified.ledconfig.configuration.parser.steps;


public class ConfigMessage {
    private final String messageText;

    public ConfigMessage(String message){
        this.messageText=message;
    }

    public String getMessageText() {
        return messageText;
    }
}
