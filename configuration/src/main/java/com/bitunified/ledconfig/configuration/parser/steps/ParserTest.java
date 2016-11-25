package com.bitunified.ledconfig.configuration.parser.steps;

import com.bitunified.ledconfig.domain.Model;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ParserTest {

    @Test
    public void testParser() {
        Parser.init();
        ParsedResult parsedResult = Parser.parse("B1M200");

        Model[] partResult = parsedResult.getParts().toArray(new Model[]{});
        System.out.println(partResult[0]);
        assertEquals("", "[[[name:liniLED Aeris Profiel L20 props:{}] MaxDim: null], [[name:liniLED RGB Deco props:{section=key:section value:}] MaxDim: [Width:200 Height:null Depth:null Unit:null]]]", parsedResult.getParts().toString());
    }
}