package com.bitunified.ledconfig.configuration.parser.steps;


import com.bitunified.ledconfig.composedproduct.ComposedProduct;
import com.bitunified.ledconfig.configuration.csvimport.Importer;
import com.bitunified.ledconfig.configuration.parser.steps.types.*;
import com.bitunified.ledconfig.domain.*;
import com.bitunified.ledconfig.domain.product.ModelResult;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;
import com.bitunified.ledconfig.domain.product.PCB.types.DecoLedStrip;
import com.bitunified.ledconfig.domain.product.PCB.types.HighPowerLedStrip;
import com.bitunified.ledconfig.domain.product.PCB.types.PowerLedStrip;
import com.bitunified.ledconfig.domain.product.accessoires.Accessoire;
import com.bitunified.ledconfig.domain.product.accessoires.CableChannel;
import com.bitunified.ledconfig.domain.product.accessoires.Clip;
import com.bitunified.ledconfig.domain.product.cable.Cable;
import com.bitunified.ledconfig.domain.product.cable.cableconfig.*;
import com.bitunified.ledconfig.domain.product.cover.Covering;
import com.bitunified.ledconfig.domain.product.cover.types.ResinClear;
import com.bitunified.ledconfig.domain.product.cover.types.ResinDiffuse;
import com.bitunified.ledconfig.domain.product.mounting.*;
import com.bitunified.ledconfig.domain.product.profile.Profile;
import com.bitunified.ledconfig.parts.NotExistingPart;
import com.bitunified.ledconfig.parts.Part;
import com.bitunified.ledconfig.parts.PartList;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parser {
    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    private Set<Part> parts = new HashSet<Part>();

    private Set<Relation> relations = new HashSet<Relation>();

    private ComposedProduct composedProduct;

    {

        createParts();
    }

    public void init() {
        parts = new HashSet<Part>();
        relations=new HashSet<Relation>();
        Importer importer = new Importer();

        try {
            PartList partList = (PartList) importer.readXml(importer.fileReader());
            parts = partList.getParts();
            relations=partList.getRelations();
            for (Part part : parts) {
                if (part.getProduct() instanceof ComposedProduct) {
                    composedProduct = (ComposedProduct) part.getProduct();
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * "10713","liniLED Aeris Profiel L20 4 m","6.81","ST","A","10"
     * "10717","liniLED Aeris Profiel H20 4 m","11.96","ST","B","10"
     * "10733","liniLED Aeris Profiel L30 4 m","9.30","ST","","10"
     * "10737","liniLED Aeris Profiel H30 4 m","14.47","ST","","10"
     * "10750","liniLED Aeris Kabelgoot 20 1 m","1.36","ST","","10"
     * "10751","liniLED Aeris Kabelgoot 20 2 m","2.71","ST","","10"
     * "10752","liniLED Aeris Kabelgoot 20 3 m","4.07","ST","","10"
     * "10753","liniLED Aeris Kabelgoot 20 4 m","5.42","ST","","10"
     * "10754","liniLED Aeris Kabelgoot 30 1 m","1.73","ST","","10"
     * "10755","liniLED Aeris Kabelgoot 30 2 m","3.45","ST","","10"
     * "10756","liniLED Aeris Kabelgoot 30 3 m","5.18","ST","","10"
     * "10757","liniLED Aeris Kabelgoot 30 4 m","6.90","ST","","10"
     * "10890","liniLED Aeris Clip 20","0.35","ST","","10"
     * "10891","liniLED Aeris Clip 30","0.35","ST","","10"
     * "10900","liniLED Aeris Eindkap L20","1.48","ST","","10"
     * "10901","liniLED Aeris Eindkap L20 O","1.51","ST","","10"
     * "10902","liniLED Aeris Eindkap LC20","1.55","ST","","10"
     * "10903","liniLED Aeris Eindkap LC20 O","1.57","ST","","10"
     * "10904","liniLED Aeris Eindkap H20","1.61","ST","","10"
     * "10905","liniLED Aeris Eindkap H20 O","1.61","ST","","10"
     * "10906","liniLED Aeris Eindkap HC20","1.63","ST","","10"
     * "10907","liniLED Aeris Eindkap HC20 O","1.63","ST","","10"
     * "10908","liniLED Aeris Eindkap C20","1.44","ST","","10"
     * "10920","liniLED Aeris Eindkap L30","1.51","ST","","10"
     * "10921","liniLED Aeris Eindkap L30 O","1.53","ST","","10"
     * "10922","liniLED Aeris Eindkap LC30","1.59","ST","","10"
     * "10923","liniLED Aeris Eindkap LC30 O","1.61","ST","","10"
     * "10924","liniLED Aeris Eindkap H30","1.59","ST","","10"
     * "10925","liniLED Aeris Eindkap H30 O","1.61","ST","","10"
     * "10926","liniLED Aeris Eindkap HC30","1.61","ST","","10"
     * "10927","liniLED Aeris Eindkap HC30 O","1.63","ST","","10"
     * "10928","liniLED Aeris Eindkap C30","1.51","ST","","10"
     * "11214","liniLED Aansluitkabel Demo","2.64","MTR","","10"
     * "21002","liniLED PCB RGB D","15.00","MTR","","10"
     * "21004","liniLED PCB Rood D","7.80","MTR","","10"
     * "21005","liniLED PCB Groen D","10.50","MTR","","10"
     * "21006","liniLED PCB Blauw D","10.50","MTR","","10"
     * "21003","liniLED PCB Amber D","7.80","MTR","","10"
     * "21017","liniLED PCB RGB P","38.60","MTR","","10"
     * "21018","liniLED PCB Rood P","16.00","MTR","","10"
     * "21020","liniLED PCB Groen P","19.00","MTR","","10"
     * "21019","liniLED PCB Blauw P","19.00","MTR","","10"
     * "21021","liniLED PCB Amber","16.00","MTR","","10"
     * "21032A","liniLED PCB UWW 2400K HP (PSP)","19.00","MTR","","10"
     * "21013A","liniLED PCB EWW 2700K HP (PSP)","19.00","MTR","","10"
     * "21014A","liniLED PCB WW 3000K HP (PSP)","19.00","MTR","","10"
     * "21015A","liniLED PCB NW 4000K HP (PSP)","19.00","MTR","","10"
     * "21016A","liniLED PCB KW 6500K HP (PSP)","19.00","MTR","","10"
     * "60000","Dubbelz Tape 6mm tbv PCB (l=50m)","4.71","ST","","10"
     * "60004","Kabel Mono Wit","0.27","MTR","","10"
     * "60005","Kabel RGB Wit","0.31","MTR","","10"
     * "60006","Kabel Mono PUR","0.78","MTR","","10"
     * "60007","Kabel RGB PUR","0.90","MTR","","10"
     * "60010","Kabel Mono PUR M12 Male 1 m","5.42","ST","","10"
     * "60011","Kabel Mono PUR M12 Male 5 m","8.92","ST","","10"
     * "60012","Kabel Mono PUR M12 Male 10 m","13.29","ST","","10"
     * "60013","Kabel RGB PUR M12 Male 1 m","6.63","ST","","10"
     * "60014","Kabel RGB PUR M12 Male 5 m","10.41","ST","","10"
     * "60015","Kabel RGB PUR M12 Male 10 m","15.68","ST","","10"
     * "95000","Ingieten liniLED L20 Helder","11.50","MTR","","10"
     * "95001","Ingieten liniLED L20 Diffuus","11.50","MTR","","10"
     * "95003","Ingieten liniLED L30 Helder","17.25","MTR","","10"
     * "95004","Ingieten liniLED L30 Diffuus","17.25","MTR","","10"
     * "95010","Ingieten liniLED H20 Helder","23.00","MTR","","10"
     * "95011","Ingieten liniLED H20 Diffuus","23.00","MTR","","10"
     * "95013","Ingieten liniLED H30 Helder","46.50","MTR","","10"
     * "95014","Ingieten liniLED H30 Diffuus","46.50","MTR","","10"
     * "ARBEID-MIN","Arbeidsminuut intern magazijn","0.59","MINUUT","","10"
     * "GRIJP","Kabeltule t.b.v. eindkap","0.07","ST","","10"
     * "GRIJP","Kabeltule t.b.v. ALU zwart","0.07","ST","","10"
     * "GRIJP","Kabeltule t.b.v. ALU wit","0.11","ST","","10"
     * "GRIJP","Productsticker","0.25","ST","","10"
     * "GRIJP","Handleiding","0.70","ST","","10"
     * "GRIJP","Verpakking (per meter)","0.50","ST","","10"
     **/
    public void createParts() {

        //Importer importer=new Importer();
        //parts=importer.importerDozer();

        Profile profileL20 = new Profile(new Dimension(null));
        profileL20.setName("liniLED Aeris Profiel L20");
        profileL20.setMaxDimension(new Dimension(2750));
        profileL20.setLeftSpace((100));
        profileL20.setLengthForCasting(new Dimension(25));
        profileL20.setCode("A");
        Part partL20 = new Part(profileL20);
        partL20.setPrice(BigDecimal.valueOf(6.81));
        partL20.setId("10713");
        partL20.setDescription("liniLED Aeris Profiel L20 4 m");
        parts.add(partL20);


        EndCap endCapLC20 = new EndCap();
        Part partEndCapLC20 = new Part(endCapLC20);
        partEndCapLC20.setPrice(BigDecimal.valueOf(1.55));
        partEndCapLC20.setId("10902");
        partEndCapLC20.setDescription("liniLED Aeris Eindkap LC20");
        parts.add(partEndCapLC20);
        profileL20.getRelation().addRelateTo(partEndCapLC20, null);


        EndCap endCapL20 = new EndCap();
        endCapL20.setName("End cap L20");
        Part partEndCapL20 = new Part(endCapL20);
        partEndCapL20.setPrice(BigDecimal.valueOf(1.48));
        partEndCapL20.setId("10900");
        partEndCapL20.setDescription("liniLED Aeris Eindkap L20");
        parts.add(partEndCapL20);
        profileL20.getRelation().addRelateTo(partEndCapL20, null);

        Profile profileH20 = new Profile(new Dimension(null));
        profileH20.setName("liniLED Aeris Profiel H20");
        profileH20.setLengthForCasting(new Dimension(25));
        profileH20.setMaxDimension(new Dimension(2750));
        profileH20.setCode("B");
        Part partProfielH20 = new Part(profileH20);
        partProfielH20.setPrice(BigDecimal.valueOf(11.96));
        partProfielH20.setId("10717");
        partProfielH20.setDescription("liniLED Aeris Profiel H20 4 m");
        parts.add(partProfielH20);




        EndCap endCapH20 = new EndCap();
        Part partEndCapH20 = new Part(endCapH20);
        partEndCapH20.setPrice(BigDecimal.valueOf(1.61));
        partEndCapH20.setId("10904");
        partEndCapH20.setDescription("liniLED Aeris Eindkap H20");
        parts.add(partEndCapH20);
        profileH20.getRelation().addRelateTo(partEndCapH20, null);

        EndCap endCapHC20 = new EndCap();
        Part partEndCapHC20 = new Part(endCapHC20);
        partEndCapHC20.setPrice(BigDecimal.valueOf(1.63));
        partEndCapHC20.setId("10906");
        partEndCapHC20.setDescription("liniLED Aeris Eindkap HC20");
        parts.add(partEndCapHC20);
        profileH20.getRelation().addRelateTo(partEndCapHC20, null);


        Part partEndCapC20 = new Part();
        partEndCapC20.setPrice(BigDecimal.valueOf(1.44));
        partEndCapC20.setId("10908");
        partEndCapC20.setDescription("liniLED Aeris Eindkap C20");
        parts.add(partEndCapC20);
        profileH20.getRelation().addRelateTo(partEndCapC20, null);
        profileL20.getRelation().addRelateTo(partEndCapC20, null);

        Part part = new Part();
        part.setPrice(BigDecimal.valueOf(1.57));
        part.setId("10903");
        part.setDescription("liniLED Aeris Eindkap LC20 O");
        parts.add(part);

        EndCap endCapL20Open = new EndCap();
        endCapL20Open.setName("End cap open L20");

        Part partEndCapL20Open = new Part(endCapL20Open);
        partEndCapL20Open.setPrice(BigDecimal.valueOf(1.51));
        partEndCapL20Open.setId("10901");
        partEndCapL20Open.setDescription("liniLED Aeris Eindkap L20 O");
        parts.add(partEndCapL20Open);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(1.61));
        part.setId("10905");
        part.setDescription("liniLED Aeris Eindkap H20 O");
        parts.add(part);



        part = new Part();
        part.setPrice(BigDecimal.valueOf(1.63));
        part.setId("10907");
        part.setDescription("liniLED Aeris Eindkap HC20 O");
        parts.add(part);



        Profile profileL30 = new Profile(new Dimension(null));
        profileL30.setName("liniLED Aeris Profiel L30");
        profileL30.setLengthForCasting(new Dimension(25));
        profileL30.setMaxDimension(new Dimension(2750));
        profileL30.setCode("C");
        Part partProfielL30 = new Part(profileL30);
        partProfielL30.setPrice(BigDecimal.valueOf(9.30));
        partProfielL30.setId("10733");
        partProfielL30.setDescription("liniLED Aeris Profiel L30 4 m");
        parts.add(partProfielL30);

        EndCap endCapL30 = new EndCap();
        Part partEndCapL30 = new Part(endCapL30);
        partEndCapL30.setPrice(BigDecimal.valueOf(1.51));
        partEndCapL30.setId("10920");
        partEndCapL30.setDescription("liniLED Aeris Eindkap L30");
        parts.add(partEndCapL30);
        partProfielL30.getRelation().addRelateTo(partEndCapL30,null);

        EndCap endCapLC30 = new EndCap();
        Part partEndCapLC30 = new Part(endCapLC30);
        partEndCapLC30.setPrice(BigDecimal.valueOf(1.59));
        partEndCapLC30.setId("10922");
        partEndCapLC30.setDescription("liniLED Aeris Eindkap LC30");
        parts.add(partEndCapLC30);
        partProfielL30.getRelation().addRelateTo(partEndCapLC30,null);

        Profile profileH30 = new Profile(new Dimension(null));
        profileH30.setName("liniLED Aeris Profiel H30");
        profileH30.setLengthForCasting(new Dimension(25));
        profileH30.setMaxDimension(new Dimension(2750));
        profileH30.setCode("D");

        Part partProfielH30 = new Part(profileH30);
        partProfielH30.setPrice(BigDecimal.valueOf(14.47));
        partProfielH30.setId("10737");
        partProfielH30.setDescription("liniLED Aeris Profiel H30 4 m");
        parts.add(partProfielH30);

        EndCap endCapH30 = new EndCap();
        Part partEndCapH30 = new Part(endCapH30);
        partEndCapH30.setPrice(BigDecimal.valueOf(1.59));
        partEndCapH30.setId("10924");
        partEndCapH30.setDescription("liniLED Aeris Eindkap H30");
        parts.add(partEndCapH30);
        profileH30.getRelation().addRelateTo(partEndCapH30,null);

        EndCap endCapHC30 = new EndCap();
        Part partEndCapHC30 = new Part(endCapHC30);
        partEndCapHC30.setPrice(BigDecimal.valueOf(1.61));
        partEndCapHC30.setId("10926");
        partEndCapHC30.setDescription("liniLED Aeris Eindkap HC30");
        parts.add(partEndCapHC30);
        profileH30.getRelation().addRelateTo(partEndCapHC30,null);

        EndCap endCapC30 = new EndCap();
        Part partEndCapC30 = new Part(endCapC30);
        partEndCapC30.setPrice(BigDecimal.valueOf(1.51));
        partEndCapC30.setId("10928");
        partEndCapC30.setDescription("liniLED Aeris Eindkap C30");
        parts.add(partEndCapC30);
        profileH30.getRelation().addRelateTo(partEndCapC30,null);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(1.53));
        part.setId("10921");
        part.setDescription("liniLED Aeris Eindkap L30 O");
        parts.add(part);



        part = new Part();
        part.setPrice(BigDecimal.valueOf(1.61));
        part.setId("10923");
        part.setDescription("liniLED Aeris Eindkap LC30 O");
        parts.add(part);



        part = new Part();
        part.setPrice(BigDecimal.valueOf(1.61));
        part.setId("10925");
        part.setDescription("liniLED Aeris Eindkap H30 O");
        parts.add(part);



        part = new Part();
        part.setPrice(BigDecimal.valueOf(1.63));
        part.setId("10927");
        part.setDescription("liniLED Aeris Eindkap HC30 O");
        parts.add(part);



        CableEntry cableEntry = new LeftViaEndCapCableEntry();
        cableEntry.setName("Left side via end cap");
        cableEntry.setCode("1");
        Margin margin = new Margin(10, 5);
        cableEntry.setMargins(margin);
        part = new NotExistingPart(cableEntry);

        part.setId("ca1");
        parts.add(part);

        cableEntry = new LeftViaBottomCableEntry();
        cableEntry.setName("Left side via bottom");
        margin = new Margin(15, 5);
        cableEntry.setMargins(margin);
        cableEntry.setCode("2");
        part = new NotExistingPart(cableEntry);
        part.setId("ca2");
        parts.add(part);

        cableEntry = new LeftViaSideCableEntry();
        cableEntry.setName("Left side via side");
        margin = new Margin(15, 5);
        cableEntry.setMargins(margin);
        cableEntry.setCode("3");
        part = new NotExistingPart(cableEntry);
        part.setId("ca3");
        parts.add(part);


        cableEntry = new RightViaSideCableEntry();
        cableEntry.setName("Rigt side via side");

        margin = new Margin(5, 15);
        cableEntry.setMargins(margin);
        cableEntry.setCode("4");
        part = new NotExistingPart(cableEntry);
        part.setId("ca4");
        parts.add(part);

        cableEntry = new CenterViaSideCableEntry();
        cableEntry.setName("Center via side");

        margin = new Margin(5, 5);
        cableEntry.setMargins(margin);
        cableEntry.setCode("5");
        part = new NotExistingPart(cableEntry);
        part.setId("ca5");
        parts.add(part);

        Mounting mounting = new NoEndCapsMounting();
        mounting.setName("Geen eindkappen");
        mounting.setCode("1");
        margin = new Margin(2, 2);
        mounting.setMargins(margin);
        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapL20, Orientation.Right);
        mounting.getRelation().addRelateTo(partEndCapH20, Orientation.Right);

        part.setId("m1");
        parts.add(part);

        mounting = new EndCapRightMounting();
        mounting.setName("Eindkap aan de rechter zijde");
        margin = new Margin(0, 2);
        mounting.setCode("2");
        mounting.setMargins(margin);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapL20,Orientation.Right);
        mounting.getRelation().addRelateTo(partEndCapH20,Orientation.Right);
        part.setId("m2");
        parts.add(part);

        mounting = new EndCapLeftMounting();
        mounting.setName("Eindkap aan linkerzijde");
        margin = new Margin(2, 0);
        mounting.setCode("3");
        mounting.setMargins(margin);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapL20,Orientation.Left);
        mounting.getRelation().addRelateTo(partEndCapH20,Orientation.Left);
        part.setId("m3");
        parts.add(part);



        mounting = new EndCapBothSidesMounting();
        mounting.setName("Eindkappen aan beide zijdes");
        margin = new Margin(2, 2);
        mounting.setCode("4");
        mounting.setMargins(margin);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapL20,null);
        mounting.getRelation().addRelateTo(partEndCapH20,null);
        mounting.getRelation().addRelateTo(partEndCapL30,null);
        mounting.getRelation().addRelateTo(partEndCapH30,null);

        part.setId("m4");
        parts.add(part);

        mounting = new HighEndCapRightMounting();
        mounting.setName("Hoge eindkap aan de rechter zijde");
        margin = new Margin(0, 2);
        mounting.setCode("5");
        mounting.setMargins(margin);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapLC20,Orientation.Right);
        mounting.getRelation().addRelateTo(partEndCapHC20,Orientation.Right);
        mounting.getRelation().addRelateTo(partEndCapLC30,Orientation.Right);
        mounting.getRelation().addRelateTo(partEndCapHC30,Orientation.Right);
        part.setId("m5");
        parts.add(part);

        mounting = new HighEndCapLeftMounting();
        mounting.setName("Hoge eindkap aan de linker zijde");
        margin = new Margin(2, 0);
        mounting.setCode("6");
        mounting.setMargins(margin);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapLC20,Orientation.Left);
        mounting.getRelation().addRelateTo(partEndCapHC20,Orientation.Left);
        mounting.getRelation().addRelateTo(partEndCapLC30,Orientation.Left);
        mounting.getRelation().addRelateTo(partEndCapHC30,Orientation.Left);
        part.setId("m6");
        parts.add(part);

        mounting = new HighEndCapBothMounting();
        mounting.setName("Hoge eindkap aan beide zijdes");
        margin = new Margin(2, 2);
        mounting.setCode("7");
        mounting.setMargins(margin);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapLC20,null);
        mounting.getRelation().addRelateTo(partEndCapHC20,null);
        mounting.getRelation().addRelateTo(partEndCapLC30,null);
        mounting.getRelation().addRelateTo(partEndCapHC30,null);
        part.setId("m7");
        parts.add(part);

        mounting = new HighEndCapBothCableChannelMounting();
        mounting.setName("Hoge eindkap aan beide zijdes van kabelgoot");
        margin = new Margin(2, 2);
        mounting.setCode("8");
        mounting.setMargins(margin);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapL20,null);
        mounting.getRelation().addRelateTo(partEndCapC20,null);
        mounting.getRelation().addRelateTo(partEndCapL30,null);
        mounting.getRelation().addRelateTo(partEndCapC30,null);
        part.setId("m8");
        parts.add(part);

        CableChannel cableChannel = new CableChannel(new Dimension(null));
        cableChannel.setName("Cable channel");
        cableChannel.setCode("b");
        part = new NotExistingPart(cableChannel);
        part.setDescription("Kabelgoot");
        part.setId("kg");
        parts.add(part);


        part = new Part();
        part.setPrice(BigDecimal.valueOf(1.36));
        part.setId("10750");
        part.setDescription("liniLED Aeris Kabelgoot 20 1 m");
        parts.add(part);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(2.71));
        part.setId("10751");
        part.setDescription("liniLED Aeris Kabelgoot 20 2 m");
        parts.add(part);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(4.07));
        part.setId("10752");
        part.setDescription("liniLED Aeris Kabelgoot 20 3 m");
        parts.add(part);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(5.42));
        part.setId("10753");
        part.setDescription("liniLED Aeris Kabelgoot 20 4 m");
        parts.add(part);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(1.73));
        part.setId("10754");
        part.setDescription("liniLED Aeris Kabelgoot 30 1 m");
        parts.add(part);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(3.45));
        part.setId("10755");
        part.setDescription("liniLED Aeris Kabelgoot 30 2 m");
        parts.add(part);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(5.18));
        part.setId("10756");
        part.setDescription("liniLED Aeris Kabelgoot 30 3 m");
        parts.add(part);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(6.9));
        part.setId("10757");
        part.setDescription("liniLED Aeris Kabelgoot 30 4 m");
        parts.add(part);



        part = new Part();
        part.setPrice(BigDecimal.valueOf(2.64));
        part.setId("11214");
        part.setDescription("liniLED Aansluitkabel Demo");
        part.setType("MTR");
        parts.add(part);


        Cable cable = new Cable(new Dimension(null));
        cable.setName("PVC with open end");
        cable.getProperty(Cable.CABLE_TYPE).setValue("PVCopenend");
        cable.setCode("1");
        part = new NotExistingPart(cable);
        part.setId("pvcw1");
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("PVC with demo connector");
        cable.getProperty(Cable.CABLE_TYPE).setValue("DemoConnector");
        cable.setCode("0");
        part = new NotExistingPart(cable);
        part.setId("pvc0dc");
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("PVC with open end 5 m");
        cable.setCode("2");
        part = new NotExistingPart(cable);
        part.setId("pvc2op5");
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("PVC with open end 10 m");
        cable.setCode("3");
        part = new NotExistingPart(cable);
        part.setId("pvc3op10");
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("PUR with open end 1 m");
        cable.setCode("4");
        part = new NotExistingPart(cable);
        part.setId("pur4op1");
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("PUR with liniLed PU Connector set");
        cable.getProperty(Cable.CABLE_TYPE).setValue("PURconnectorset");
        cable.setCode("7");
        part = new NotExistingPart(cable);
        part.setId("purw7");
        parts.add(part);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(9.6));
        part.setId("60009");
        parts.add(part);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(0.27));
        part.setId("60004");
        part.setDescription("Kabel Mono Wit");
        parts.add(part);
//        "60004","Kabel Mono Wit","0.27","MTR","","10"

        part = new Part();
        part.setPrice(BigDecimal.valueOf(0.31));
        part.setId("60005");
        part.setDescription("Kabel RGB Wit");
        parts.add(part);

//        "60005","Kabel RGB Wit","0.31","MTR","","10"

        part = new Part(cable);
        part.setPrice(BigDecimal.valueOf(0.9));
        part.setType("MTR");
        part.setId("60006");
        part.setDescription("Kabel Mono PUR");
        parts.add(part);
//        "60006","Kabel Mono PUR","0.78","MTR","","10"
        part = new Part(cable);
        part.setPrice(BigDecimal.valueOf(0.9));
        part.setType("MTR");
        part.setId("60007");
        part.setDescription("Kabel RGB PUR");
        parts.add(part);
//        "60007","Kabel RGB PUR","0.90","MTR","","10"

//        "60010","Kabel Mono PUR M12 Male 1 m","5.42","ST","","10"
//        "60011","Kabel Mono PUR M12 Male 5 m","8.92","ST","","10"
//        "60012","Kabel Mono PUR M12 Male 10 m","13.29","ST","","10"
//        "60013","Kabel RGB PUR M12 Male 1 m","6.63","ST","","10"
//        "60014","Kabel RGB PUR M12 Male 5 m","10.41","ST","","10"
//        "60015","Kabel RGB PUR M12 Male 10 m","15.68","ST","","10"


        //(GABHPQRSTUV){1}
        Covering covering = new Covering(null);
        covering.setName("Geen kap");
        covering.setCode("G");
        part = new NotExistingPart(covering);
        part.setId("co1");
        parts.add(part);

        covering = new ResinClear(null);
        covering.setName("Lage kap helder L20");
        covering.setCode("A");
        part = new Part(covering);
        part.setId("95000");
        part.setDescription("Ingieten liniLED L20 Helder");
        part.setPrice(BigDecimal.valueOf(11.5));
        part.setType("MTR");
        parts.add(part);
        profileL20.getRelation().addRelateTo(part,null);
        //        "95000","Ingieten liniLED L20 Helder","11.50","MTR","","10"

        covering = new ResinDiffuse(null);
        covering.setName("Lage kap diffuus L20");
        covering.setCode("B");
        part = new Part(covering);
        part.setId("95001");
        part.setPrice(BigDecimal.valueOf(11.5));
        part.setDescription("Ingieten liniLED L20 Diffuus");
        part.setType("MTR");
        parts.add(part);
        profileL20.getRelation().addRelateTo(part,null);
//        "95001","Ingieten liniLED L20 Diffuus","11.50","MTR","","10"


        covering = new ResinClear(null);
        covering.setName("Hoge kap helder L30");
        covering.setCode("H");
        part = new Part(covering);
        part.setDescription("Ingieten liniLED L30 Helder");
        part.setPrice(BigDecimal.valueOf(17.25));
        part.setType("MTR");
        part.setId("95003");
        parts.add(part);
        profileL30.getRelation().addRelateTo(part,null);
        //        "95003","Ingieten liniLED L30 Helder","17.25","MTR","","10"


        covering = new ResinDiffuse(null);
        covering.setName("L30 Diffuus");
        covering.setCode("P");
        part = new Part(covering);
        part.setDescription("Ingieten liniLED L30 Diffuus");
        part.setId("95004");
        part.setPrice(BigDecimal.valueOf(17.25));
        part.setType("MTR");
        parts.add(part);
        profileL30.getRelation().addRelateTo(part,null);
        //        "95004","Ingieten liniLED L30 Diffuus","17.25","MTR","","10"


        covering = new ResinClear(null);
        covering.setName("H20 Helder");
        covering.setCode("Q");
        part = new Part(covering);
        part.setPrice(BigDecimal.valueOf(23));
        part.setId("95010");
        part.setDescription("Ingieten liniLED H20 Helder");
        part.setType("MTR");
        parts.add(part);
        profileH20.getRelation().addRelateTo(part,null);
//        "95010","Ingieten liniLED H20 Helder","23.00","MTR","","10"

        covering = new ResinDiffuse(null);
        covering.setName("H20 Diffuus");
        covering.setCode("R");
        part = new Part(covering);
        part.setPrice(BigDecimal.valueOf(23));
        part.setId("95011");
        part.setDescription("Ingieten liniLED H20 Diffuus");
        part.setType("MTR");
        parts.add(part);
        profileH20.getRelation().addRelateTo(part,null);
        //        "95011","Ingieten liniLED H20 Diffuus","23.00","MTR","","10"

        covering = new ResinClear(null);
        covering.setName("H30 Helder");
        covering.setCode("S");
        part = new Part(covering);
        part.setPrice(BigDecimal.valueOf(46.5));
        part.setId("95013");
        part.setDescription("Ingieten liniLED H30 Helder");
        part.setType("MTR");
        parts.add(part);
        profileH30.getRelation().addRelateTo(part,null);
//        "95013","Ingieten liniLED H30 Helder","46.50","MTR","","10"
        covering = new ResinDiffuse(null);
        covering.setName("H30 Diffuus");
        covering.setCode("U");
        part = new Part(covering);
        part.setPrice(BigDecimal.valueOf(46.5));
        part.setId("95014");
        part.setDescription("Ingieten liniLED H30 Diffuus");
        part.setType("MTR");
        parts.add(part);
        profileH30.getRelation().addRelateTo(part,null);
//        "95014","Ingieten liniLED H30 Diffuus","46.50","MTR","","10"


        //(DPHN){1}(RGBDA|2473456){1}
        LedStrip ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Deco rood");
        ledStrip.setMaxDimension(new Dimension(2600));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(DecoLedStrip.COLOR).setValue("rood");
        ledStrip.setCode("DR");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(7.8));
        part.setId("21004");
        part.setDescription("liniLED PCB Rood D");
        part.setType("MTR");
        parts.add(part);

        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED RGB Deco");
        ledStrip.setMaxDimension(new Dimension(2600));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(DecoLedStrip.COLOR).setValue("rgb");
        ledStrip.setCode("DD");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(15));
        part.setId("21002");
        part.setType("MTR");
        part.setDescription("liniLED PCB RGB D");
        parts.add(part);
        //"21002","liniLED PCB RGB D","15.00","MTR","","10"

        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Deco groen");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(DecoLedStrip.COLOR).setValue("groen");
        ledStrip.setCode("PG");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(10.5));
        part.setId("21005");
        part.setType("MTR");
        part.setDescription("liniLED PCB Groen D");
        parts.add(part);
        //"21005","liniLED PCB Groen D","10.50","MTR","","10"

        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Deco blauw");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(DecoLedStrip.COLOR).setValue("blauw");
        ledStrip.setCode("PB");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(10.5));
        part.setId("21006");
        part.setType("MTR");
        part.setDescription("liniLED PCB Blauw D");
        parts.add(part);
        //"21006","liniLED PCB Blauw D","10.50","MTR","","10"


        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Deco amber");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(DecoLedStrip.COLOR).setValue("amber");
        ledStrip.setCode("PA");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(7.8));
        part.setId("21003");
        part.setType("MTR");
        part.setDescription("liniLED PCB Amber D");
        parts.add(part);
        //"21003","liniLED PCB Amber D","7.80","MTR","","10"

        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Power RGB");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.setCode("PP");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(38.6));
        part.setId("21017");
        part.setType("MTR");
        part.setDescription("liniLED PCB RGB P");
        parts.add(part);

        //"21017","liniLED PCB RGB P","38.60","MTR","","10"


        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Power rood");
        ledStrip.setMaxDimension(new Dimension(2670));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(PowerLedStrip.COLOR_TYPE).setValue("rood");
        ledStrip.setCode("PR");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(16));
        part.setId("21018");
        part.setType("MTR");
        part.setDescription("liniLED PCB Rood P");
        parts.add(part);

        //"21018","liniLED PCB Rood P","16.00","MTR","","10"


        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Power groen");
        ledStrip.setMaxDimension(new Dimension(2670));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(PowerLedStrip.COLOR_TYPE).setValue("groen");
        ledStrip.setCode("PG");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(19));
        part.setId("21020");
        part.setType("MTR");
        part.setDescription("liniLED PCB Groen P");
        parts.add(part);

        //"21020","liniLED PCB Groen P","19.00","MTR","","10"


        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Power blauw");
        ledStrip.setMaxDimension(new Dimension(2670));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.setCode("PB");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(19));
        part.setId("21019");
        part.setType("MTR");
        part.setDescription("liniLED PCB Blauw P");
        parts.add(part);
        //"21019","liniLED PCB Blauw P","19.00","MTR","","10"

        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Power amber");
        ledStrip.setMaxDimension(new Dimension(2670));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.setCode("PA");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(19));
        part.setId("21021");
        part.setType("MTR");
        part.setDescription("liniLED PCB amber P");
        parts.add(part);

        //"21021","liniLED PCB Amber","16.00","MTR","","10"

        ledStrip = new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED HP 2400K UWW");
        ledStrip.setMaxDimension(new Dimension(2670));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2400");
        ledStrip.setCode("H4");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(19));
        part.setId("21032A");
        part.setType("MTR");
        part.setDescription("liniLED PCB EWW 2700K HP (PSP)");
        parts.add(part);
        //"21032A","liniLED PCB UWW 2400K HP (PSP)","19.00","MTR","","10"

        ledStrip = new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED HP 2700K EWW");
        ledStrip.setMaxDimension(new Dimension(2670));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("H7");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(19));
        part.setId("21013A");
        part.setType("MTR");
        part.setDescription("liniLED PCB EWW 2700K HP (PSP)");
        parts.add(part);
        //"21013A","liniLED PCB EWW 2700K HP (PSP)","19.00","MTR","","10"

        ledStrip = new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED HP 3000K WW");
        ledStrip.setMaxDimension(new Dimension(2670));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("3000");
        ledStrip.setCode("H3");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(19));
        part.setId("21014A");
        part.setType("MTR");
        part.setDescription("liniLED PCB WW 3000K HP (PSP)");
        parts.add(part);
        //"21014A","liniLED PCB WW 3000K HP (PSP)","19.00","MTR","","10"

        ledStrip = new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED HP 4000K WW");
        ledStrip.setMaxDimension(new Dimension(2670));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("4000");
        ledStrip.setCode("H4");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(19));
        part.setId("21015A");
        part.setType("MTR");
        part.setDescription("liniLED PCB NW 4000K HP (PSP)");
        parts.add(part);
        //"21015A","liniLED PCB NW 4000K HP (PSP)","19.00","MTR","","10"

        ledStrip = new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED HP 6500K KW");
        ledStrip.setMaxDimension(new Dimension(2670));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("6500");
        ledStrip.setCode("H6");

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(19));
        part.setId("21016A");
        part.setType("MTR");
        part.setDescription("liniLED PCB KW 6500K HP (PSP)");
        parts.add(part);

        part = new Part(null);
        part.setPrice(BigDecimal.valueOf(4.71));
        part.setId("60000");
        part.setType("ST");
        part.setDescription("Dubbelz Tape 6mm tbv PCB (l=50m)");
        parts.add(part);


        //"60000","Dubbelz Tape 6mm tbv PCB (l=50m)","4.71","ST","","10"


        Clip clip = new Clip();
        clip.setName("liniLED Aeris Clip 20");

        part = new Part(clip);
        part.setId("10890");
        part.setDescription("Clip 20");
        parts.add(part);

        clip = new Clip();
        clip.setName("liniLED Aeris Clip 30");

        part = new Part(clip);
        part.setId("10891");
        part.setPrice(BigDecimal.valueOf(0.35));
        part.setDescription("Clip 30");
        parts.add(part);

        clip = new Clip();
        clip.setName("Accessoire: Clip");
        clip.setCode("a");
        part = new NotExistingPart(clip);
        part.setId("clip");
        part.setDescription("Clip.");
        parts.add(part);


        composedProduct = new ComposedProduct(null, null);
        composedProduct.setName("Product lengte");
        part = new Part(composedProduct);
        part.setId("comp");
        parts.add(part);

    }


    //B1M348D20077778a
    //B([321]){1}([MRGBAPCDEF123456])([1470258369])([1-5])([1-8])([CD])(\d{4})((\d{4})?)(([ab])?)
    //C([ABCDEF]){1}([GABHPQ]){1}([DPHN]){1}([RGBA]|[2473456]){1}([1470258369])([1-5])([1-8])(\d{4})((\d{4})?)(([ab])?)
    ///1: Profiel (B1)
    //2: Led Strip type
    //3: Cable type
    public ParsedResult parse(String productcode) {
        List<ParseStep> steps = new ArrayList<ParseStep>();
        Set<Model> models = new HashSet<Model>();
        steps.add(new ParserStepRealModel(1, true, 1, 2, Profile.class, "", "Profiel niet geconfigureerd"));
        steps.add(new ParserStepRealModel(2, true, 2, 3, Covering.class, "", "Behuizing niet geconfigureerd"));
        steps.add(new ParserStepRealModel(3, true, 3, 5, LedStrip.class, "", "Kleur niet geconfigureerd"));
        steps.add(new ParserStepRealModel(4, true, 5, 6, Cable.class, "", "Kabel niet geconfigureerd"));
        steps.add(new ParserStepModel(5, true, 6, 7, CableEntry.class, "", "Kabeluiteinde niet geconfigureerd"));
        steps.add(new ParserStepModel(6, true, 7, 8, Mounting.class, "", "Montage opties niet geconfigureerd"));
        steps.add(new ParserStepDimensionModel(7, true, 8, 12, LedStrip.class, "", "Led strip lengte niet geconfigureerd", models));

        steps.add(new ParserStepRealModelComposed(8, ComposedProduct.class, "", "Productlengte automatisch berekend.", 12, 16, models));
        steps.add(new ParserStepModelRightParse(9, true, 0, 1, Accessoire.class, "", "Accessoire opties niet geconfigureerd"));

        for (ParseStep step : steps) {
            ModelResult createdModel = step.create(productcode, parts);

            if (createdModel != null) {
                models.add(createdModel.getModel());

            }
            step.addModelResult(createdModel);
            composedProduct.addModelResult(createdModel);

        }

        ParsedResult parsedResult = new ParsedResult();
        parsedResult.setModels(models);
        parsedResult.setParts(parts);
        parsedResult.setSteps(steps);
        return parsedResult;
    }

    public Set<Relation> getRelations() {
        return relations;
    }
}
