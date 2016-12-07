package com.bitunified.server.message;


import com.bitunified.ledconfig.domain.message.Message;

import java.util.HashMap;
import java.util.Map;

public class ServerResponse {
    private String success;

    private String errorMessage;
    private String[] messages;
    private Map<Integer,Message> messageMap=new HashMap<>();

    public ServerResponse(String success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    public Map<Integer, Message> getMessageMap() {
        return messageMap;
    }

    public void setMessageMap(Map<Integer,Message> messagesMap) {

//        for (Map.Entry message:messagesMap.entrySet()){
//            messageMap.put((Integer)message.getKey(),((Message)message.getValue()).getMessage());
//        }
    this.messageMap=messagesMap;
    }
}
