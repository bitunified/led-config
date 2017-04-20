package com.bitunified.ledconfig;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LedConfigTest {


    //CABDD1473000b

    @Test
    public void testConfig1() {
        String[] codes = new String[]{
                "DEHUABD2000a"
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
    public void test_Cable_mono_not_with_RGB() {
        String[] args = new String[]{"DERPKBC02000230b"};
        LedConfig ledConfig = new LedConfig();
        ConfigResult result = ledConfig.rules(args);
        System.out.println(result);

        result.getMessages().stream().forEach(f-> {
                    System.out.println(f.getMessage());
                    assertEquals("Check error", "Kabel met mono connector kan niet in combinatie met RGB Ledstrip.", f.getMessage());
                }
        );
    }

    @Test
    public void test_Warning_Not_Diffuse() {
        // 1. Laag profiel (L20/L30)
        // 2. Diffuse lage kap/Diffuse ingegoten
        // 3. Deco, Power, Photon of RGB PCB
        String[] args = new String[]{"DDDGKBC02000230b"};
        LedConfig ledConfig = new LedConfig();
        ConfigResult result = ledConfig.rules(args);

        result.getMessages().stream().forEach(f-> {
                    System.out.println(f.getMessage());
                    assertEquals("Check error", "Deco,Power, HP ledstrip niet mogelijk voor ingieten.", f.getMessage());
                }
        );
        assertEquals("Check # messages",1,result.getMessages().size());
        args = new String[]{"DLDGKBC02000230b"};
        ledConfig = new LedConfig();
        result = ledConfig.rules(args);

        result.getMessages().stream().forEach(f-> {
                    System.out.println(f.getMessage());
                    assertEquals("Check error", "Deco,Power, HP ledstrip niet mogelijk voor ingieten.", f.getMessage());
                }
        );
        assertEquals("Check # messages",1,result.getMessages().size());
    }
}