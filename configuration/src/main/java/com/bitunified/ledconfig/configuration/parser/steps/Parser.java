package com.bitunified.ledconfig.configuration.parser.steps;



import com.bitunified.ledconfig.composedproduct.ComposedProduct;
import com.bitunified.ledconfig.configuration.parser.steps.types.ParserStepDimensionModel;
import com.bitunified.ledconfig.configuration.parser.steps.types.ParserStepModel;
import com.bitunified.ledconfig.configuration.parser.steps.types.ParserStepRealModel;
import com.bitunified.ledconfig.configuration.parser.steps.types.ParserStepRealModelComposed;
import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.product.ModelResult;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;
import com.bitunified.ledconfig.domain.product.PCB.types.DecoLedStrip;
import com.bitunified.ledconfig.domain.product.PCB.types.HighPowerLedStrip;
import com.bitunified.ledconfig.domain.product.cable.Cable;
import com.bitunified.ledconfig.domain.product.cable.cableconfig.CableEntry;
import com.bitunified.ledconfig.domain.product.cover.Covering;
import com.bitunified.ledconfig.domain.product.mounting.Mounting;
import com.bitunified.ledconfig.domain.product.profile.Profile;
import com.bitunified.ledconfig.parts.Part;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parser {
    private List<Part> parts=new ArrayList<Part>();

private ComposedProduct composedProduct;
    {
       init();
    }
    public void init(){
        parts=new ArrayList<Part>();
        createParts();

    }

    private void createParts(){
        Profile profile = new Profile(new Dimension(null));
        profile.setName("liniLED Aeris Profiel L20");
        Part part = new Part(profile);
        part.setCode("A");
        part.setPrice(BigDecimal.TEN);
        parts.add(part);

        profile = new Profile(new Dimension(100,200));
        profile.setName("liniLED Aeris Profiel H20");
        part = new Part(profile);
        part.setPrice(BigDecimal.TEN);
        part.setCode("B");
        parts.add(part);

        profile = new Profile(new Dimension(100,200));
        profile.setName("liniLED Aeris Profiel L30");
        part = new Part(profile);
        part.setPrice(BigDecimal.TEN);
        part.setCode("C");
        parts.add(part);

        profile = new Profile(new Dimension(100,200));
        profile.setName("liniLED Aeris Profiel H30");
        part = new Part(profile);
        part.setPrice(BigDecimal.TEN);
        part.setCode("D");
        parts.add(part);


        Cable cable=new Cable(new Dimension(null));
        cable.setName("PVC with ope end");
        cable.getProperty(Cable.CABLE_TYPE).setValue("PVCopenend");
        part=new Part(cable);
        part.setPrice(BigDecimal.valueOf(8.9));
        part.setCode("1");
        parts.add(part);

        cable=new Cable(new Dimension(null));
        cable.setName("PUR with liniLed PU Connector set");
        cable.getProperty(Cable.CABLE_TYPE).setValue("PURconnectorset");
        part=new Part(cable);
        part.setPrice(BigDecimal.valueOf(9.6));
        part.setCode("7");
        parts.add(part);

        CableEntry cableEntry=new CableEntry();
        cableEntry.setName("Left side via end cap");
        part=new Part(cableEntry);
        part.setCode("1");
        part.setPrice(BigDecimal.valueOf(7.1));
        parts.add(part);

        cableEntry=new CableEntry();
        cableEntry.setName("Left side via bottom");
        part=new Part(cableEntry);
        part.setPrice(BigDecimal.valueOf(6.2));
        part.setCode("2");
        parts.add(part);

        Mounting mounting=new Mounting();
        mounting.setName("No end caps");
        part=new Part(mounting);
        part.setCode("1");
        part.setPrice(BigDecimal.valueOf(5.4));
        parts.add(part);

         mounting=new Mounting();
        mounting.setName("End cap on right side");
        part=new Part(mounting);
        part.setCode("2");
        part.setPrice(BigDecimal.valueOf(5.35));
        parts.add(part);

        mounting=new Mounting();
        mounting.setName("End caps both side");
        part=new Part(mounting);
        part.setCode("4");
        part.setPrice(BigDecimal.valueOf(5.21));
        parts.add(part);

        //(GABHPQ){1}
        Covering covering=new Covering(null);
        covering.setName("Geen kap");
        part=new Part(covering);
        part.setCode("G");
        //part.setPrice(BigDecimal.valueOf(0));
        parts.add(part);

        covering=new Covering(null);
        covering.setName("Lage kap helder");
        part=new Part(covering);
        part.setCode("A");
        part.setPrice(BigDecimal.valueOf(3.92));
        parts.add(part);

        covering=new Covering(null);
        covering.setName("Lage kap diffuus");
        part=new Part(covering);
        part.setCode("B");
        part.setPrice(BigDecimal.valueOf(3.42));
        parts.add(part);

        covering=new Covering(null);
        covering.setName("Hoge kap diffuus");
        part=new Part(covering);
        part.setCode("H");
        part.setPrice(BigDecimal.valueOf(2.12));
        parts.add(part);

        covering=new Covering(null);
        covering.setName("PU Helder");
        part=new Part(covering);
        part.setCode("P");
        part.setPrice(BigDecimal.valueOf(4.32));
        parts.add(part);

        covering=new Covering(null);
        covering.setName("PU Diffuus");
        part=new Part(covering);
        part.setCode("Q");
        part.setPrice(BigDecimal.valueOf(8.12));
        parts.add(part);


        //(DPHN){1}(RGBA|2473456){1}
        LedStrip ledStrip=new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED RGB Deco rood");
        ledStrip.setMaxDimension(new Dimension(10));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(DecoLedStrip.COLOR).setValue("rood");
        part=new Part(ledStrip);
        part.setCode("DR");
        part.setPrice(BigDecimal.valueOf(12.5));
        parts.add(part);

        ledStrip=new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED RGB Deco blauw");
        ledStrip.setMaxDimension(new Dimension(10));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(DecoLedStrip.COLOR).setValue("blauw");
        part=new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(13.2));
        part.setCode("DB");
        parts.add(part);

        ledStrip=new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Power blauw");
        ledStrip.setMaxDimension(new Dimension(10));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(DecoLedStrip.COLOR).setValue("blauw");
        part=new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(13.2));
        part.setCode("PB");
        parts.add(part);

        ledStrip=new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Power 2400K");
        ledStrip.setMaxDimension(new Dimension(10));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2400");
        part=new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(14.1));
        part.setCode("P4");
        parts.add(part);

        ledStrip=new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED HighPower 2000K");
        ledStrip.setMaxDimension(new Dimension(10));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2000");
        part=new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(14.1));
        part.setCode("H2");
        parts.add(part);

        ledStrip=new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED HighPower 2400K");
        ledStrip.setMaxDimension(new Dimension(10));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2400");
        part=new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(14.1));
        part.setCode("H4");
        parts.add(part);

        ledStrip=new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED HighPower 2700K");
        ledStrip.setMaxDimension(new Dimension(10));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        part=new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(14.1));
        part.setCode("H7");
        parts.add(part);

        ledStrip=new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Photon 2000K");
        ledStrip.setMaxDimension(new Dimension(10));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2000");
        part=new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(14.1));
        part.setCode("N2");
        parts.add(part);

        composedProduct=new ComposedProduct(null,null);
        composedProduct.setName("ComposedProduct");
        part=new Part(composedProduct);
        part.setPrice(BigDecimal.valueOf(20.59));
        parts.add(part);

    }
    //B1M348D20077778a
    //B([321]){1}([MRGBAPCDEF123456])([1470258369])([1-5])([1-8])([CD])(\d{4})((\d{4})?)(([ab])?)
    //C([ABCDEF]){1}([GABHPQ]){1}([DPHN]){1}([RGBA]|[2473456]){1}([1470258369])([1-5])([1-8])(\d{4})((\d{4})?)(([ab])?)
    ///1: Profiel (B1)
    //2: Led Strip type
    //3: Cable type
    public ParsedResult parse(String productcode){
        List<ParseStep> steps =new ArrayList<ParseStep>();
        Set<Model> models=new HashSet<Model>();
        steps.add(new ParserStepRealModel(1,true,1,2,Profile.class,"","Profiel niet geconfigureerd"));
        steps.add(new ParserStepRealModel(2,true,2,3,Covering.class,"","Behuizing niet geconfigureerd"));
        steps.add(new ParserStepRealModel(3,true,3,5,LedStrip.class,"","Kleur niet geconfigureerd"));
        steps.add(new ParserStepRealModel(4,true,5,6,Cable.class,"","Kabel niet geconfigureerd"));
        steps.add(new ParserStepModel(5,true,6,7,CableEntry.class,"","Kabeluiteinde niet geconfigureerd"));
        steps.add(new ParserStepModel(6,true,7,8,Mounting.class,"","Montage opties niet geconfigureerd"));
        steps.add(new ParserStepDimensionModel(7,true,8,12,LedStrip.class,"","Led strip lengte niet geconfigureerd",models));

        steps.add(new ParserStepRealModelComposed(8,ComposedProduct.class,"","Productlengte niet geconfigureerd",12,16,models));

        for (ParseStep step:steps){
            ModelResult createdModel=step.create(productcode,parts);

            if (createdModel!=null){
                models.add(createdModel.getModel());

            }
            step.addModelResult(createdModel);
            composedProduct.addModelResult(createdModel);
        }

        ParsedResult parsedResult=new ParsedResult();
        parsedResult.setModels(models);
        parsedResult.setParts(parts);
        parsedResult.setSteps(steps);
        return parsedResult;
    }
}
