package com.bitunified.ledconfig;


import com.bitunified.ledconfig.domain.message.Message;
import com.bitunified.ledconfig.parts.Part;
import org.drools.core.impl.StatefulKnowledgeSessionImpl;

import java.util.*;

public class ConfigResult {
    private List<Message> messages=new ArrayList<Message>();
    private Map<Integer,List<Message>> messageMap=new HashMap<>();
    private List models=new ArrayList<StatefulKnowledgeSessionImpl.ObjectStoreWrapper>();
    private Set<Part> partList;
    public ConfigResult(List<Message> messages, Map<Integer,List<Message>> messageMap, Collection<?> objects,Set<Part> partList) {
        this.messages=messages;
        this.messageMap=messageMap;
        this.models.addAll(objects);
        this.partList=partList;
    }


    public List getModels() {
        return models;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Map<Integer, List<Message>> getMessageMap() {
        return messageMap;
    }

    public Set<Part> getPartList() {
        return partList;
    }
}
