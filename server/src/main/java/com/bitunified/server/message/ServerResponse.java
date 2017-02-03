package com.bitunified.server.message;


import com.bitunified.ledconfig.domain.instruction.InstructionMessage;
import com.bitunified.ledconfig.domain.message.Message;
import com.bitunified.ledconfig.domain.message.PartCount;
import com.bitunified.ledconfig.parts.Part;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerResponse {
    private String success;

    private String errorMessage;
    private String[] messages;
    private Map<Integer, List<Message>> messageMap = new HashMap<>();

    private List<PartCount> partList=new ArrayList<PartCount>();


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

    public void setMessageMap(Map<Integer, List<Message>> messagesMap) {

//        for (Map.Entry message:messagesMap.entrySet()){
//            messageMap.put((Integer)message.getKey(),((Message)message.getValue()).getMessage());
//        }
        this.messageMap = messagesMap;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }


    public List<PartCount> getPartList() {
        return partList;
    }

    public void setPartList(List<PartCount> partList) {
        this.partList = partList;
    }

    public void addPartList(Map<Part, Double> partList) {
        for (Part p : partList.keySet()) {
            if (p != null && p.getPrice() != null) {
                this.partList.add(new PartCount( p,partList.get(p)));
            }
        }

    }

    public List<InstructionMessage> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<InstructionMessage> instructions) {
        this.instructions = instructions;
    }
}
