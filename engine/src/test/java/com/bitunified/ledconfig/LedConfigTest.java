package com.bitunified.ledconfig;

import org.junit.Test;


public class LedConfigTest {


    //CABDD1473000b

    @Test
    public void testConfig1() {
        String[] codes = new String[]{
                "DDHUABD2000b"
                //"AGDR11302000300a"};
        };
        for (String code : codes) {
            execute(code);
        }
    }

    private void execute(String code) {
        String[] args = new String[]{code};
        LedConfig ledConfig = new LedConfig();
        ConfigResult result = ledConfig.rules(args);
        System.out.println(result.getMessages());
        System.out.println(result.getModels());
    }

    @Test
    public void testConfig2() {
        String[] args = new String[]{"AGDR11102000230b"};
        LedConfig ledConfig = new LedConfig();
        ConfigResult result = ledConfig.rules(args);
        System.out.println(result.getMessages());
        System.out.println(result.getModels());
        //assertEquals("","[Message: Totale lengte is groter dan ledstrip lengte inclusief marges. - Status: 0, Message: Totale lengte is groter dan maximale ledstrip lengte. - Status: 0]",messages);
    }
}