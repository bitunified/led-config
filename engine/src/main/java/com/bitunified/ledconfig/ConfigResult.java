package com.bitunified.ledconfig;


import com.bitunified.ledconfig.domain.message.Message;
import org.drools.core.impl.StatefulKnowledgeSessionImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConfigResult {
    private List<Message> messages=new ArrayList<Message>();
    private List models=new ArrayList<StatefulKnowledgeSessionImpl.ObjectStoreWrapper>();
    public ConfigResult(List<Message> messages, Collection<?> objects) {
        this.messages=messages;
        this.models.addAll(objects);
    }




    public List<Message> getMessages() {
        return messages;
    }
}
