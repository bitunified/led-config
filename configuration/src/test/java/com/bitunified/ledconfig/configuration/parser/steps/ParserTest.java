package com.bitunified.ledconfig.configuration.parser.steps;

import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.parts.Part;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ParserTest {
    Parser parser = new Parser();

    @Ignore
    @Test
    public void testParser() {


        parser.init();
        ParsedResult parsedResult = parser.parse("CAGDR11102000300a");

        System.out.println(parsedResult.getParts().toString());
        //assertEquals("", "[[[name:liniLED Aeris Profiel L20 props:{}], Dimension:[Width:null Height:null Depth:null Unit:null] MaxDim: null], [[name:liniLED RGB Deco props:{section=key:section value:200}], Dimension:[Width:200 Height:null Depth:null Unit:null] MaxDim: [Width:10 Height:null Depth:null Unit:null]], [[name:null props:{}], Dimension:[Width:300 Height:null Depth:null Unit:null] MaxDim: null]]", parsedResult.getParts().toString());
    }
}