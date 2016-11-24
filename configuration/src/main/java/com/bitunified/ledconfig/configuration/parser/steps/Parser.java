package com.bitunified.ledconfig.configuration.parser.steps;



import com.bitunified.ledconfig.composedproduct.ComposedProduct;
import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;
import com.bitunified.ledconfig.domain.product.PCB.types.DecoLedStrip;
import com.bitunified.ledconfig.domain.product.cable.Cable;
import com.bitunified.ledconfig.domain.product.cover.types.ResinClear;
import com.bitunified.ledconfig.domain.product.profile.Profile;
import com.bitunified.ledconfig.parts.Part;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public  Parser(){
        createParts();
    }
    private static List<Part> parts=new ArrayList<Part>();

    private static void createParts(){
         Profile profile = new Profile(new Dimension(100,200));
        profile.setName("liniLED Aeris Profiel L20");
        Part profilePart = new Part(profile);
        profilePart.setCode("1");
        parts.add(profilePart);

        profile = new Profile(new Dimension(100,200));
        profile.setName("liniLED Aeris Profiel H20");
         profilePart = new Part(profile);
        profilePart.setCode("2");
        parts.add(profilePart);


    }
    public static List<Model> parse(String productcode){



        List<ParserStep> steps =new ArrayList<ParserStep>();

        steps.add(new ParserStep(0,1,Profile.class,""));

        List<Model> models=new ArrayList<Model>();
        for (ParserStep step:steps){
            models.add(step.create(productcode,parts));
        }
        return models;
    }
}
