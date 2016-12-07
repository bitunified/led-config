package com.bitunified.ledconfig.configuration.parser.steps;

import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.parts.Part;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ParserTest {

    @Test
    public void testParser() {
        Parser.init();
        ParsedResult parsedResult = Parser.parse("B13714D02000219a");

        Part[] partResult = parsedResult.getParts().toArray(new Part[]{});
        System.out.println(partResult);
        //assertEquals("", "[[[name:liniLED Aeris Profiel L20 props:{}], Dimension:[Width:null Height:null Depth:null Unit:null] MaxDim: null], [[name:liniLED RGB Deco props:{section=key:section value:200}], Dimension:[Width:200 Height:null Depth:null Unit:null] MaxDim: [Width:10 Height:null Depth:null Unit:null]], [[name:null props:{}], Dimension:[Width:300 Height:null Depth:null Unit:null] MaxDim: null]]", parsedResult.getParts().toString());
    }
}