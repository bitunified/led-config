package com.bitunified.server;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ConfigApplicationTest {


    @Test
    public void testRules() throws IOException {
        ConfigApplication configApplication = new ConfigApplication();
        File file = getTempFile("LedConfig.drl");
        if (file.exists()){
            file.delete();
        }
        String result=configApplication.updateRules();

        assertEquals(result,"Rules updates have been successfully applied!");

        assertTrue(file.exists());


    }

    private File getTempFile(String name) throws IOException {
        File baseDir = new File(System.getProperty("java.io.tmpdir"));

        File tempFile = new File(baseDir, name);
        return tempFile;
    }
}