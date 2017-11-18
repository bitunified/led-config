package com.bitunified.ledconfig.configuration.config;

import com.bitunified.ledconfig.configuration.parser.steps.ParserDataResult;
import com.sun.xml.internal.stream.writers.UTF8OutputStreamWriter;
import groovy.lang.GroovyClassLoader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;


public class GroovyCompiler {

    public ParserDataResult compile(OutputStream outputStream) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, IOException {
        //String script = "import com.bitunified.ledconfig.configuration.config.Executor;import com.bitunified.ledconfig.configuration.parser.steps.ParserDataResult; public class ExecImpl implements Executor{ public ParserDataResult execute(){return new ParserDataResult(null,null,null); }}";
        String script = getStringFromStream(outputStream);
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        Executor executor;

        Class theParsedClass = groovyClassLoader.parseClass(script);
        executor = (Executor) theParsedClass.newInstance();

        if (executor != null) {

            return executor.execute();
        }
        return null;
    }

    private String getStringFromStream(OutputStream outputStream) throws IOException {
        Writer w = new OutputStreamWriter(outputStream, "UTF-8");
        return w.toString();
    }
}
