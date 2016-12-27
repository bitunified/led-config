package com.bitunified.ledconfig;

import org.junit.Test;


public class LedConfigTest {

    @Test
    public void testConfig(){
        String[] args=new String[]{"CAGDR11102002500b"};
        LedConfig ledConfig=new LedConfig();
        ConfigResult result=ledConfig.rules(args);
        System.out.println(result.getMessages());
        System.out.println(result.getModels());
        //assertEquals("","[Message: Totale lengte is groter dan ledstrip lengte inclusief marges. - Status: 0, Message: Totale lengte is groter dan maximale ledstrip lengte. - Status: 0]",messages);
    }
}