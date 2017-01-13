package com.bitunified.server.message;


import com.bitunified.ledconfig.domain.message.Message;
import com.bitunified.ledconfig.parts.Part;

import java.math.BigDecimal;
import java.util.*;

public class ServerResponse {
    private String success;

    private String errorMessage;
    private String[] messages;
    private Map<Integer,List<Message>> messageMap=new HashMap<>();
    private List<Part> partList=new ArrayList<>();

    private Double totalPrice;

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

    public Map<Integer, List<Message>> getMessageMap() {
        return messageMap;
    }

    public void setMessageMap(Map<Integer,List<Message>> messagesMap) {

//        for (Map.Entry message:messagesMap.entrySet()){
//            messageMap.put((Integer)message.getKey(),((Message)message.getValue()).getMessage());
//        }
    this.messageMap=messagesMap;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public List<Part> getPartList() {
        return partList;
    }

    public void setPartList(List<Part> partList) {
        this.partList = partList;
    }
}
