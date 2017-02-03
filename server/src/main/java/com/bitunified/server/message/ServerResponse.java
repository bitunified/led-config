package com.bitunified.server.message;


import com.bitunified.ledconfig.domain.instruction.InstructionMessage;
import com.bitunified.ledconfig.domain.message.Message;
import com.bitunified.ledconfig.parts.Part;
import com.bitunified.ledconfig.parts.SimplePart;

import java.math.BigDecimal;
import java.util.*;

public class ServerResponse {
    private String success;

    private String errorMessage;
    private String[] messages;
    private Map<Integer,List<Message>> messageMap=new HashMap<>();
    private Map<SimplePart,Double> partList=new HashMap<>();
    private List<InstructionMessage> instructions;

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

    public Map<SimplePart,Double> getPartList() {
        return partList;
    }

    public void setPartList(Map<Part,Double> partList) {
        for (Part p:partList.keySet()){
            this.partList.put(new SimplePart(p.getPrice().doubleValue(),p.getDescription()), partList.get(p));
        }

    }

    public List<InstructionMessage> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<InstructionMessage> instructions) {
        this.instructions = instructions;
    }
}
