package com.bitunified.ledconfig;


import com.bitunified.ledconfig.domain.message.Message;

import java.util.ArrayList;
import java.util.List;

public class ConfigResult {
    private List<Message> messages=new ArrayList<Message>();

    public ConfigResult(List<Message> messages) {
        this.messages=messages;
    }


    public List<Message> getMessages() {
        return messages;
    }
}
