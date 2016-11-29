package com.bitunified.ledconfig;

import com.bitunified.ledconfig.domain.message.Message;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class LedConfigTest {

    @Test
    public void testConfig(){
        String[] args=new String[]{"B13714D02000219a"};
        List<Message> messages=LedConfig.rules(args);
        //assertEquals("","[Message: Totale lengte is groter dan ledstrip lengte inclusief marges. - Status: 0, Message: Totale lengte is groter dan maximale ledstrip lengte. - Status: 0]",messages);
    }
}