package com.bitunified.ledconfig.configuration.csvimport;

import org.junit.Test;

import java.io.IOException;


public class ImporterTest {

    @Test
    public void test(){
        Importer importer=new Importer();
        importer.importFromCSVFile();
    }

    @Test
    public void test3() throws IOException {
        Importer importer=new Importer();
        importer.importerDozer();
    }
}