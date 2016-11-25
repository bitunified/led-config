package com.bitunified.ledconfig.configuration.parser.steps;



import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;
import com.bitunified.ledconfig.domain.product.profile.Profile;
import com.bitunified.ledconfig.parts.Part;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static List<Part> parts=new ArrayList<Part>();
    static{
       init();
    }
    public static void init(){
        parts=new ArrayList<Part>();
        createParts();

    }

    private static void createParts(){
        Profile profile = new Profile(new Dimension(null));
        profile.setName("liniLED Aeris Profiel L20");
        Part part = new Part(profile);
        part.setCode("1");
        parts.add(part);

        profile = new Profile(new Dimension(100,200));
        profile.setName("liniLED Aeris Profiel H20");

        part = new Part(profile);
        part.setCode("2");

        LedStrip ledStrip=new LedStrip(new Dimension(null));
        ledStrip.setName("liniLED RGB Deco");
        ledStrip.setMaxDimension(new Dimension(300));
        part=new Part(ledStrip);
        part.setCode("M");

        parts.add(part);
    }
    public static ParsedResult parse(String productcode){
        List<ParserStep> steps =new ArrayList<ParserStep>();
        steps.add(new ParserStep(1,2,Profile.class,"","Profiel niet geconfigureerd", null));
        steps.add(new ParserStep(2,3,LedStrip.class,"","Ledstrip niet geconfigureerd", 3+3));
        List<Model> models=new ArrayList<Model>();
        List<String> messages=new ArrayList<String>();
        for (ParserStep step:steps){
            Model createdModel=step.create(productcode,parts);
            if (createdModel!=null){
                models.add(createdModel);
            }else{
                messages.add(step.getErrorMessage());
            }
        }
        ParsedResult parsedResult=new ParsedResult();
        parsedResult.setParts(models);

        return parsedResult;
    }
}
