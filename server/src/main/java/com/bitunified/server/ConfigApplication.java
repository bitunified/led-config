package com.bitunified.server;

import com.bitunified.ledconfig.configuration.config.GroovyCompiler;
import com.bitunified.ledconfig.configuration.parser.steps.Data;
import com.bitunified.ledconfig.configuration.parser.steps.Parser;
import com.bitunified.ledconfig.configuration.parser.steps.ParserDataResult;
import com.bitunified.server.google.Download;


public class ConfigApplication {

    public String updateData() {
        String message;
        StringBuilder sb = new StringBuilder();
        Data.parser = new Parser();
        Download download = new Download();
        GroovyCompiler groovyCompiler = new GroovyCompiler();
        ParserDataResult dataResult = null;
        try {
            dataResult = groovyCompiler.compile(download.getParserDataFile());
        } catch (Exception e) {
            System.out.print(e);
            sb.append(e);
        }
        Data.parser.createParts(dataResult);
        if (sb.length() == 0) {
            sb.append("Successfull updated data");
        }
        message = sb.toString();

        return message;
    }
}
