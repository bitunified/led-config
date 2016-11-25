package com.bitunified.ledconfig.configuration.parser.steps;


import com.bitunified.ledconfig.domain.Model;

import java.util.ArrayList;
import java.util.List;

public class ParsedResult {

    private List<Model> parts=new ArrayList<Model>();
    private List<ConfigMessage> messages=new ArrayList<ConfigMessage>();

    public List<ConfigMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ConfigMessage> messages) {
        this.messages = messages;
    }

    public List<Model> getParts() {
        return parts;
    }

    public void setParts(List<Model> parts) {
        this.parts = parts;
    }
}
