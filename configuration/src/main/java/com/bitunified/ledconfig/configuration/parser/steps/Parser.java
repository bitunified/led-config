package com.bitunified.ledconfig.configuration.parser.steps;



import com.bitunified.ledconfig.composedproduct.ComposedProduct;
import com.bitunified.ledconfig.configuration.parser.steps.types.ParserStepRealModel;
import com.bitunified.ledconfig.configuration.parser.steps.types.ParserStepRealModelComposed;
import com.bitunified.ledconfig.configuration.parser.steps.types.ParserStepRealModelWithLength;
import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;
import com.bitunified.ledconfig.domain.product.PCB.types.DecoLedStrip;
import com.bitunified.ledconfig.domain.product.PCB.types.HighPowerLedStrip;
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


        LedStrip ledStrip=new DecoLedStrip(new Dimension(null));
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

        ledStrip=new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("High Power");
        ledStrip.setMaxDimension(new Dimension(10));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("3000");
        part=new Part(ledStrip);
        part.setCode("3");
        parts.add(part);

        Cable cable=new Cable(new Dimension(null));
        cable.setName("PVC with ope end");
        cable.getProperty(Cable.CABLE_TYPE).setValue("PVCopenend");
        part=new Part(cable);
        part.setCode("1");
        parts.add(part);

        cable=new Cable(new Dimension(null));
        cable.setName("PUR with liniLed PU Connector set");
        cable.getProperty(Cable.CABLE_TYPE).setValue("PURconnectorset");
        part=new Part(cable);
        part.setCode("7");
        parts.add(part);

        CableEntry cableEntry=new CableEntry();
        cable.setName("Left side via end cap");
        part=new Part(cableEntry);
        part.setCode("1");
        parts.add(part);

        cableEntry=new CableEntry();
        cableEntry.setName("Left side via bottom");
        part=new Part(cableEntry);
        part.setCode("2");
        parts.add(part);

        Mounting mounting=new Mounting();
        mounting.setName("No end caps");
        part=new Part(mounting);
        part.setCode("1");
        parts.add(part);

         mounting=new Mounting();
        mounting.setName("End cap on right side");
        part=new Part(mounting);
        part.setCode("2");
        parts.add(part);

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
        steps.add(new ParserStepRealModel(true,1,2,Profile.class,"","Stap 1: Profiel niet geconfigureerd"));
        steps.add(new ParserStepRealModelWithLength(2,3,LedStrip.class,"","Stap 2: Ledstrip niet geconfigureerd", 7,11,"Stap 7: Led strip lengte niet geconfigureerd"));
        steps.add(new ParserStepRealModel(true,3,4,Cable.class,"","Stap 3: Kabel niet geconfigureerd"));
        steps.add(new ParserStepRealModel(true,4,5,CableEntry.class,"","Stap 4: Kabeluiteinde niet geconfigureerd"));
        steps.add(new ParserStepRealModel(true,5,6,Mounting.class,"","Stap 5: Montage opties niet geconfigureerd"));
        steps.add(new ParserStepRealModel(true,6,7,Covering.class,"","Stap 6: Behuizing niet geconfigureerd"));

        steps.add(new ParserStepRealModelComposed(ComposedProduct.class,"","Stap 8: Productlengte niet geconfigureerd",11,15));
        List<Model> models=new ArrayList<Model>();
        List<ConfigMessage> messages=new ArrayList<ConfigMessage>();
        for (ParseStep step:steps){
            ModelResult createdModel=step.create(productcode,parts);
            if (createdModel!=null && createdModel.getErrorMessage()==null){
                models.add(createdModel.getModel());
            }else{
                if (!step.isMantatory()) {
                    messages.add(new ConfigMessage(createdModel.getErrorMessage()));
                }
            }
        }
        ParsedResult parsedResult=new ParsedResult();
        parsedResult.setParts(models);
        parsedResult.setMessages(messages);
        return parsedResult;
    }
}
