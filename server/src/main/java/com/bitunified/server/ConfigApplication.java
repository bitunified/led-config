package com.bitunified.server;

import com.bitunified.ledconfig.configuration.config.GroovyCompiler;
import com.bitunified.ledconfig.configuration.parser.steps.Data;
import com.bitunified.ledconfig.configuration.parser.steps.Parser;
import com.bitunified.ledconfig.configuration.parser.steps.ParserDataResult;
import com.bitunified.server.google.Download;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;


public class ConfigApplication {

    public String updateData() {
        String fileNameStart = "ParserData";
        String message;
        StringBuilder sb = new StringBuilder();

        Download download = new Download();
        GroovyCompiler groovyCompiler = new GroovyCompiler();

        try {
            ParserDataResult dataResult = groovyCompiler.compile(download.getParserDataFile(fileNameStart));
            if (dataResult != null) {
                Data.parser = new Parser();
                Data.parser.createParts(dataResult);
            }
        } catch (Exception e) {
            System.out.print(e);
            sb.append(e);
        }
        if (sb.length() == 0) {
            sb.append("Data updates have been successfully applied!");
        }
        message = sb.toString();

        return message;
    }

    public String updateRules() {
        String message;
        StringBuilder sb = new StringBuilder();
        Download download = new Download();
        try {
            OutputStream outputStream = download.getParserDataFile("LedConfig");
            createTempDir("LedConfig.drl", outputStream);
        } catch (Exception e) {
            System.out.print(e);
            sb.append(e);
        }
        if (sb.length() == 0) {
            sb.append("Rules updates have been successfully applied!");
        }
        message = sb.toString();

        return message;
    }

    private void createTempDir(String name, OutputStream outputStream) throws IOException {
        File baseDir = new File(System.getProperty("java.io.tmpdir"));

        File tempFile = new File(baseDir, name);

        try {
            FileWriter f2 = new FileWriter(tempFile, false);
            f2.write(outputStream.toString());
            f2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
