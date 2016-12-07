package com.bitunified.ledconfig;


import com.bitunified.ledconfig.domain.message.Message;
import org.drools.core.impl.StatefulKnowledgeSessionImpl;

import java.util.*;

public class ConfigResult {
    private List<Message> messages=new ArrayList<Message>();
    private Map<Integer,Message> messageMap=new HashMap<>();
    private List models=new ArrayList<StatefulKnowledgeSessionImpl.ObjectStoreWrapper>();
    public ConfigResult(List<Message> messages, Map<Integer,Message> messageMap, Collection<?> objects) {
        this.messages=messages;
        this.messageMap=messageMap;
        this.models.addAll(objects);
    }


    public List getModels() {
        return models;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Map<Integer, Message> getMessageMap() {
        return messageMap;
    }
}
