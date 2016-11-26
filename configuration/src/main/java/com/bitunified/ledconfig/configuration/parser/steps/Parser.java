package com.bitunified.ledconfig.configuration.parser.steps;



import com.bitunified.ledconfig.composedproduct.ComposedProduct;
import com.bitunified.ledconfig.configuration.parser.steps.types.ParserStepRealModel;
import com.bitunified.ledconfig.configuration.parser.steps.types.ParserStepRealModelComposed;
import com.bitunified.ledconfig.configuration.parser.steps.types.ParserStepRealModelWithLength;
import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;
import com.bitunified.ledconfig.domain.product.PCB.types.DecoLedStrip;
import com.bitunified.ledconfig.domain.product.cable.Cable;
import com.bitunified.ledconfig.domain.product.cable.cableconfig.CableEntry;
import com.bitunified.ledconfig.domain.product.cover.Covering;
import com.bitunified.ledconfig.domain.product.mounting.Mounting;
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
        parts.add(part);


        DecoLedStrip ledStrip=new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED RGB Deco");
        ledStrip.setMaxDimension(new Dimension(10));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        part=new Part(ledStrip);
        part.setCode("M");
        parts.add(part);

        ledStrip=new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Red Deco");
        ledStrip.setMaxDimension(new Dimension(10));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(DecoLedStrip.COLOR).setValue("red");
        part=new Part(ledStrip);
        part.setCode("R");
        parts.add(part);

        Cable cable=new Cable(new Dimension(null));
        cable.setName("cable");
        cable.getProperty(Cable.CABLE_TYPE).setValue("PVCopenend");
        part=new Part(cable);
        part.setCode("M");

        ComposedProduct composedProduct=new ComposedProduct(null,null);

        part=new Part(composedProduct);


        parts.add(part);
    }
    //B1M348D20077778a
    //B([321]){1}([MRGBAPCDEF123456])([1470258369])([1-5])([1-8])([CD])(\d{4})((\d{4})?)(([ab])?)
    //1: Profiel (B1)
    //2: Led Strip type
    //3: Cable type
    public static ParsedResult parse(String productcode){
        List<ParseStep> steps =new ArrayList<ParseStep>();
        steps.add(new ParserStepRealModel(1,2,Profile.class,"","Profiel niet geconfigureerd"));
        steps.add(new ParserStepRealModelWithLength(2,3,LedStrip.class,"","Ledstrip niet geconfigureerd", 6,9));
        steps.add(new ParserStepRealModel(3,4,Cable.class,"","Kabel niet geconfigureerd"));
        steps.add(new ParserStepRealModel(4,5,CableEntry.class,"","Kabeluiteinde niet geconfigureerd"));
        steps.add(new ParserStepRealModel(5,6,Mounting.class,"","Montage opties niet geconfigureerd"));
        steps.add(new ParserStepRealModel(5,6,Covering.class,"","Behuizing niet geconfigureerd"));

        steps.add(new ParserStepRealModelComposed(ComposedProduct.class,"","Ledstrip niet geconfigureerd",10,14));
        List<Model> models=new ArrayList<Model>();
        List<ConfigMessage> messages=new ArrayList<ConfigMessage>();
        for (ParseStep step:steps){
            Model createdModel=step.create(productcode,parts);
            if (createdModel!=null){
                models.add(createdModel);
            }else{
                messages.add(new ConfigMessage(step.getErrorMessage()));
            }
        }
        ParsedResult parsedResult=new ParsedResult();
        parsedResult.setParts(models);
        parsedResult.setMessages(messages);
        return parsedResult;
    }
}
