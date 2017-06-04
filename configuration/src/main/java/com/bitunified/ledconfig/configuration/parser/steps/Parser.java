package com.bitunified.ledconfig.configuration.parser.steps;


import com.bitunified.ledconfig.composedproduct.ComposedProduct;
import com.bitunified.ledconfig.configuration.Accumulator;
import com.bitunified.ledconfig.configuration.csvimport.Importer;
import com.bitunified.ledconfig.configuration.parser.steps.types.*;
import com.bitunified.ledconfig.domain.*;
import com.bitunified.ledconfig.domain.I18N.Locale;
import com.bitunified.ledconfig.domain.product.ModelResult;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;
import com.bitunified.ledconfig.domain.product.PCB.types.*;
import com.bitunified.ledconfig.domain.product.accessoires.Accessoire;
import com.bitunified.ledconfig.domain.product.accessoires.CableChannel;
import com.bitunified.ledconfig.domain.product.accessoires.Clip;
import com.bitunified.ledconfig.domain.product.cable.Cable;
import com.bitunified.ledconfig.domain.product.cable.cableconfig.*;
import com.bitunified.ledconfig.domain.product.cover.Covering;
import com.bitunified.ledconfig.domain.product.cover.types.*;
import com.bitunified.ledconfig.domain.product.mounting.*;
import com.bitunified.ledconfig.domain.product.profile.Profile;
import com.bitunified.ledconfig.domain.relation.RelationDefinition;
import com.bitunified.ledconfig.domain.relation.RelationState;
import com.bitunified.ledconfig.parts.NotExistingPart;
import com.bitunified.ledconfig.parts.Part;
import com.bitunified.ledconfig.parts.PartList;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class Parser {
    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    private Set<Part> parts = new HashSet<Part>();

    private Set<Relation> relations = new HashSet<Relation>();

    private List<RelationDefinition> relationDefinitions = new ArrayList<RelationDefinition>();

    private List<Model> models = new ArrayList<Model>();

    private ComposedProduct composedProduct;

    public void init() {
        parts = new HashSet<Part>();
        relations = new HashSet<Relation>();
        Importer importer = new Importer();

        try {
            PartList partList = (PartList) importer.readXml(importer.fileReader());
            parts = partList.getParts();
            relations = partList.getRelations();
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
        profileL20.setCode("D");
        profileL20.setStep(1);
        models.add(profileL20);

        RelationDefinition relationDefinitionL20 = createRelationDefinition();
        relationDefinitionL20.addModel(profileL20);


        Part partL20 = new Part(profileL20);
        partL20.setPrice(BigDecimal.valueOf(36.09));
        partL20.setId("10713");
        partL20.setDescription("liniLED Aeris Profiel L20 4 m");
        parts.add(partL20);


        EndCap endCap = new EndCap();
        models.add(endCap);
        Part partEndCapLC20 = new Part(endCap);
        partEndCapLC20.setPrice(BigDecimal.valueOf(8.22));
        partEndCapLC20.setId("10902");
        partEndCapLC20.setDescription("liniLED Aeris Eindkap LC20");
        parts.add(partEndCapLC20);
        profileL20.getRelation().addRelateTo(partEndCapLC20, null);


        Part partEndCapL20 = new Part(endCap);
        partEndCapL20.setPrice(BigDecimal.valueOf(7.84));
        partEndCapL20.setId("10900");
        partEndCapL20.setDescription("liniLED Aeris Eindkap L20");
        parts.add(partEndCapL20);
        profileL20.getRelation().addRelateTo(partEndCapL20, null);

        Profile profileH20 = new Profile(new Dimension(null));
        profileH20.setName("liniLED Aeris Profiel H20");
        profileH20.setLengthForCasting(new Dimension(25));
        profileH20.setMaxDimension(new Dimension(2750));
        profileH20.setCode("E");
        models.add(profileH20);
        Part partProfielH20 = new Part(profileH20);
        partProfielH20.setPrice(BigDecimal.valueOf(63.39));
        partProfielH20.setId("10717");
        partProfielH20.setDescription("liniLED Aeris Profiel H20 4 m");
        parts.add(partProfielH20);


        Part partEndCapH20 = new Part(endCap);
        partEndCapH20.setPrice(BigDecimal.valueOf(8.43));
        partEndCapH20.setId("10904");
        partEndCapH20.setDescription("liniLED Aeris Eindkap H20");
        parts.add(partEndCapH20);
        profileH20.getRelation().addRelateTo(partEndCapH20, null);

        Part partEndCapHC20 = new Part(endCap);
        partEndCapHC20.setPrice(BigDecimal.valueOf(8.53));
        partEndCapHC20.setId("10906");
        partEndCapHC20.setDescription("liniLED Aeris Eindkap HC20");
        parts.add(partEndCapHC20);
        profileH20.getRelation().addRelateTo(partEndCapHC20, null);


        Part partEndCapC20 = new Part(endCap);
        partEndCapC20.setPrice(BigDecimal.valueOf(7.63));
        partEndCapC20.setId("10908");
        partEndCapC20.setDescription("liniLED Aeris Eindkap C20");
        parts.add(partEndCapC20);
        profileH20.getRelation().addRelateTo(partEndCapC20, null);
        profileL20.getRelation().addRelateTo(partEndCapC20, null);

        Part part = new Part(endCap);
        part.setPrice(BigDecimal.valueOf(8.32));
        part.setId("10903");
        part.setDescription("liniLED Aeris Eindkap LC20 O");
        parts.add(part);


        Part partEndCapL20Open = new Part(endCap);
        partEndCapL20Open.setPrice(BigDecimal.valueOf(8.00));
        partEndCapL20Open.setId("10901");
        partEndCapL20Open.setDescription("liniLED Aeris Eindkap L20 O");
        parts.add(partEndCapL20Open);

        part = new Part(endCap);
        part.setPrice(BigDecimal.valueOf(8.53));
        part.setId("10905");
        part.setDescription("liniLED Aeris Eindkap H20 O");
        parts.add(part);


        part = new Part(endCap);
        part.setPrice(BigDecimal.valueOf(8.64));
        part.setId("10907");
        part.setDescription("liniLED Aeris Eindkap HC20 O");
        parts.add(part);


        Cap capDiffuse;
        capDiffuse = new CapDiffuse();
        models.add(capDiffuse);
        capDiffuse.setName("liniLED® Aeris Afdekkap");
        capDiffuse.setTranslations(Locale.nl, capDiffuse.getName());
        capDiffuse.setTranslations(Locale.en, "liniLED® Aeris Cap");
        capDiffuse.getProperty(Cap.TRANSLUCENCY_S).setValue("diffuse");
        capDiffuse.setCode("L");
        relationDefinitionL20.addModel(capDiffuse);

        Part partCapL20 = new Part(capDiffuse);
        partCapL20.setPrice(BigDecimal.valueOf(40.92));
        partCapL20.setId("10803");
        partCapL20.setDescription("liniLED® Aeris Kap D L20 4 m");
        parts.add(partCapL20);


        Cap capClear = new CapClear(new Dimension(null));
        models.add(capClear);
        capClear.setName("liniLED® Aeris Afdekkap");
        capClear.setTranslations(Locale.nl, capClear.getName());
        capClear.setTranslations(Locale.en, "liniLED® Aeris Cap C L20 4 m");
        capClear.setCode("R");
        relationDefinitionL20.addModel(capClear);

        partCapL20 = new Part(capClear);
        partCapL20.setPrice(BigDecimal.valueOf(35.62));
        partCapL20.setId("10807");
        partCapL20.setDescription("liniLED® Aeris Kap C L20 4 m");
        parts.add(partCapL20);


        partCapL20 = new Part(capDiffuse);
        partCapL20.setPrice(BigDecimal.valueOf(54.11));
        partCapL20.setId("10811");
        partCapL20.setDescription("liniLED® Aeris Kap D H20 4 m");
        parts.add(partCapL20);


        partCapL20 = new Part(capDiffuse);
        partCapL20.setPrice(BigDecimal.valueOf(43.46));
        partCapL20.setId("10823");
        partCapL20.setDescription("liniLED® Aeris Kap D L30 4 m");
        parts.add(partCapL20);


        partCapL20 = new Part(capClear);
        partCapL20.setPrice(BigDecimal.valueOf(37.74));
        partCapL20.setId("10827");
        partCapL20.setDescription("liniLED® Aeris Kap C L30 4 m");
        parts.add(partCapL20);



        partCapL20 = new Part(capDiffuse);
        partCapL20.setPrice(BigDecimal.valueOf(55.76));
        partCapL20.setId("10831");
        partCapL20.setDescription("liniLED® Aeris Kap D H30 4 m");
        parts.add(partCapL20);

        Profile profileL30 = new Profile(new Dimension(null));
        models.add(profileL30);
        profileL30.setName("liniLED Aeris Profiel L30");
        profileL30.setTranslations(Locale.nl, profileL30.getName());
        profileL30.setTranslations(Locale.en, "liniLed Aeris Profile L30");
        profileL30.setLengthForCasting(new Dimension(25));
        profileL30.setMaxDimension(new Dimension(2750));
        profileL30.setCode("F");


        Part partProfielL30 = new Part(profileL30);

        partProfielL30.setPrice(BigDecimal.valueOf(49.29));
        partProfielL30.setId("10733");
        partProfielL30.setDescription("liniLED Aeris Profiel L30 4 m");
        parts.add(partProfielL30);

        Part partEndCapL30 = new Part(endCap);
        partEndCapL30.setPrice(BigDecimal.valueOf(8.00));
        partEndCapL30.setId("10920");
        partEndCapL30.setDescription("liniLED Aeris Eindkap L30");
        parts.add(partEndCapL30);
        partProfielL30.getRelation().addRelateTo(partEndCapL30, null);


        Part partEndCapLC30 = new Part(endCap);
        partEndCapLC30.setPrice(BigDecimal.valueOf(8.43));
        partEndCapLC30.setId("10922");
        partEndCapLC30.setDescription("liniLED Aeris Eindkap LC30");
        parts.add(partEndCapLC30);
        partProfielL30.getRelation().addRelateTo(partEndCapLC30, null);

        Profile profileH30 = new Profile(new Dimension(null));
        models.add(profileH30);
        profileH30.setName("liniLED Aeris Profiel H30");
        profileH30.setLengthForCasting(new Dimension(25));
        profileH30.setMaxDimension(new Dimension(2750));
        profileH30.setCode("G");

        Part partProfielH30 = new Part(profileH30);
        partProfielH30.setPrice(BigDecimal.valueOf(76.69));
        partProfielH30.setId("10737");
        partProfielH30.setDescription("liniLED Aeris Profiel H30 4 m");
        parts.add(partProfielH30);


        Part partEndCapH30 = new Part(endCap);
        partEndCapH30.setPrice(BigDecimal.valueOf(8.43));
        partEndCapH30.setId("10924");
        partEndCapH30.setDescription("liniLED Aeris Eindkap H30");
        parts.add(partEndCapH30);
        profileH30.getRelation().addRelateTo(partEndCapH30, null);


        Part partEndCapHC30 = new Part(endCap);
        partEndCapHC30.setPrice(BigDecimal.valueOf(8.53));
        partEndCapHC30.setId("10926");
        partEndCapHC30.setDescription("liniLED Aeris Eindkap HC30");
        parts.add(partEndCapHC30);
        profileH30.getRelation().addRelateTo(partEndCapHC30, null);


        Part partEndCapC30 = new Part(endCap);
        partEndCapC30.setPrice(BigDecimal.valueOf(7.90));
        partEndCapC30.setId("10928");
        partEndCapC30.setDescription("liniLED Aeris Eindkap C30");
        parts.add(partEndCapC30);
        profileH30.getRelation().addRelateTo(partEndCapC30, null);

        part = new Part(endCap);
        part.setPrice(BigDecimal.valueOf(8.11));
        part.setId("10921");
        part.setDescription("liniLED Aeris Eindkap L30 O");
        parts.add(part);


        part = new Part(endCap);
        part.setPrice(BigDecimal.valueOf(8.53));
        part.setId("10923");
        part.setDescription("liniLED Aeris Eindkap LC30 O");
        parts.add(part);


        part = new Part(endCap);
        part.setPrice(BigDecimal.valueOf(8.53));
        part.setId("10925");
        part.setDescription("liniLED Aeris Eindkap H30 O");
        parts.add(part);


        part = new Part();
        part.setPrice(BigDecimal.valueOf(8.64));
        part.setId("10927");
        part.setDescription("liniLED Aeris Eindkap HC30 O");
        part.setTranslations(Locale.nl, part.getDescription());
        part.setTranslations(Locale.en, "liniLED Aeris Endcap HC30 O");
        parts.add(part);


        CableEntry cableEntry = new LeftViaEndCapCableEntry();
        models.add(cableEntry);
        cableEntry.setName("Left side via end cap");
        cableEntry.setCode("A");
        Margin margin = new Margin(10, 5);
        cableEntry.setMargins(margin);
        part = new NotExistingPart(cableEntry);

        part.setId("ca1");
        parts.add(part);

        cableEntry = new LeftViaBottomCableEntry();
        cableEntry.setName("Left side via bottom");
        margin = new Margin(15, 5);
        cableEntry.setMargins(margin);
        cableEntry.setCode("B");
        part = new NotExistingPart(cableEntry);
        part.setId("ca2");
        parts.add(part);

        cableEntry = new LeftViaSideCableEntry();
        cableEntry.setName("Left side via side");
        margin = new Margin(15, 5);
        cableEntry.setMargins(margin);
        cableEntry.setCode("C");
        part = new NotExistingPart(cableEntry);
        part.setId("ca3");
        parts.add(part);


        cableEntry = new RightViaSideCableEntry();
        cableEntry.setName("Rigt side via side");

        margin = new Margin(5, 15);
        cableEntry.setMargins(margin);
        cableEntry.setCode("D");
        part = new NotExistingPart(cableEntry);
        part.setId("ca4");
        parts.add(part);

        cableEntry = new CenterViaSideCableEntry();
        cableEntry.setName("Center via side");

        margin = new Margin(5, 5);
        cableEntry.setMargins(margin);
        cableEntry.setCode("E");
        part = new NotExistingPart(cableEntry);
        part.setId("ca5");
        parts.add(part);

        Mounting mounting = new NoEndCapsMounting();
        mounting.setName("Geen eindkappen");
        mounting.setTranslations(Locale.nl, "Geen eindkappen");
        mounting.setTranslations(Locale.en, "No endcaps");
        mounting.setCode("A");
        margin = new Margin(2, 2);
        mounting.setMargins(margin);
        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapL20, Orientation.Right);
        mounting.getRelation().addRelateTo(partEndCapH20, Orientation.Right);

        part.setId("m1");
        parts.add(part);

        mounting = new EndCapRightMounting();
        mounting.setName("Eindkap aan de rechter zijde");
        mounting.setTranslations(Locale.nl, mounting.getName());
        mounting.setTranslations(Locale.en, "Endcap right side");
        margin = new Margin(0, 2);
        mounting.setCode("B");
        mounting.setMargins(margin);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapL20, Orientation.Right);
        mounting.getRelation().addRelateTo(partEndCapH20, Orientation.Right);
        part.setId("m2");
        parts.add(part);

        mounting = new EndCapLeftMounting();
        mounting.setName("Eindkap aan linkerzijde");
        mounting.setTranslations(Locale.nl, mounting.getName());
        mounting.setTranslations(Locale.en, "Endcap left side");
        margin = new Margin(2, 0);
        mounting.setCode("C");
        mounting.setMargins(margin);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapL20, Orientation.Left);
        mounting.getRelation().addRelateTo(partEndCapH20, Orientation.Left);
        part.setId("m3");
        parts.add(part);


        mounting = new EndCapBothSidesMounting();
        mounting.setName("Eindkappen aan beide zijdes");
        mounting.setTranslations(Locale.nl, mounting.getName());
        mounting.setTranslations(Locale.en, "Endcaps both sides");
        margin = new Margin(2, 2);
        mounting.setCode("D");
        mounting.setMargins(margin);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapL20, null);
        mounting.getRelation().addRelateTo(partEndCapH20, null);
        mounting.getRelation().addRelateTo(partEndCapL30, null);
        mounting.getRelation().addRelateTo(partEndCapH30, null);

        part.setId("m4");
        parts.add(part);

        mounting = new HighEndCapRightMounting();
        mounting.setName("Hoge eindkap aan de rechter zijde");
        margin = new Margin(0, 2);
        mounting.setCode("E");
        mounting.setMargins(margin);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapLC20, Orientation.Right);
        mounting.getRelation().addRelateTo(partEndCapHC20, Orientation.Right);
        mounting.getRelation().addRelateTo(partEndCapLC30, Orientation.Right);
        mounting.getRelation().addRelateTo(partEndCapHC30, Orientation.Right);
        part.setId("m5");
        parts.add(part);

        mounting = new HighEndCapLeftMounting();
        mounting.setName("Hoge eindkap aan de linker zijde");
        margin = new Margin(2, 0);
        mounting.setCode("F");
        mounting.setMargins(margin);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapLC20, Orientation.Left);
        mounting.getRelation().addRelateTo(partEndCapHC20, Orientation.Left);
        mounting.getRelation().addRelateTo(partEndCapLC30, Orientation.Left);
        mounting.getRelation().addRelateTo(partEndCapHC30, Orientation.Left);
        part.setId("m6");
        parts.add(part);

        mounting = new HighEndCapBothMounting();
        mounting.setName("Hoge eindkap aan beide zijdes");
        margin = new Margin(2, 2);
        mounting.setCode("G");
        mounting.setMargins(margin);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapLC20, null);
        mounting.getRelation().addRelateTo(partEndCapHC20, null);
        mounting.getRelation().addRelateTo(partEndCapLC30, null);
        mounting.getRelation().addRelateTo(partEndCapHC30, null);
        part.setId("m7");
        parts.add(part);

        mounting = new HighEndCapBothCableChannelMounting();
        mounting.setName("Hoge eindkap aan beide zijdes van kabelgoot");
        margin = new Margin(2, 2);
        mounting.setCode("H");
        mounting.setMargins(margin);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapL20, null);
        mounting.getRelation().addRelateTo(partEndCapC20, null);
        mounting.getRelation().addRelateTo(partEndCapL30, null);
        mounting.getRelation().addRelateTo(partEndCapC30, null);
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
        part.setPrice(BigDecimal.valueOf(7.16
        ));
        part.setId("10750");
        part.setDescription("liniLED Aeris Kabelgoot 20 1 m");
        parts.add(part);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(14.36));
        part.setId("10751");
        part.setDescription("liniLED Aeris Kabelgoot 20 2 m");
        parts.add(part);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(21.52));
        part.setId("10752");
        part.setDescription("liniLED Aeris Kabelgoot 20 3 m");
        parts.add(part);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(28.73));
        part.setId("10753");
        part.setDescription("liniLED Aeris Kabelgoot 20 4 m");
        parts.add(part);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(9.86));
        part.setId("10754");
        part.setDescription("liniLED Aeris Kabelgoot 30 1 m");
        parts.add(part);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(19.66));
        part.setId("10755");
        part.setDescription("liniLED Aeris Kabelgoot 30 2 m");
        parts.add(part);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(29.52));
        part.setId("10756");
        part.setDescription("liniLED Aeris Kabelgoot 30 3 m");
        parts.add(part);

        part = new Part();
        part.setPrice(BigDecimal.valueOf(39.38));
        part.setId("10757");
        part.setDescription("liniLED Aeris Kabelgoot 30 4 m");
        parts.add(part);

        Cable cable = new Cable(new Dimension(null));
        cable.setName("liniLED Aansluitkabel Mono Demo 1 m");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("mono");
        cable.setTranslations(Locale.en, "liniLED connector cable Mono Demo 1 m");
        cable.setCode("K");
        part = new Part(cable);
        part.setPrice(BigDecimal.valueOf(13.99));
        part.setId("11214");
        part.setDescription("liniLED Aansluitkabel Mono Demo 1 m");
        part.setType("MTR");
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("Kabel Mono Wit 1 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("mono");
        cable.setCode("A");
        part = new Part(cable);
        part.setId("60004");
        part.setPrice(BigDecimal.valueOf(1.43));
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("Kabel Mono Wit 5 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("mono");
        cable.setCode("B");
        part = new Part(cable);
        part.setId("60004b");
        part.setPrice(BigDecimal.valueOf(7.15));
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Kabel Mono Wit 10 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("mono");
        cable.setCode("C");
        part = new Part(cable);
        part.setId("60004c");
        part.setPrice(BigDecimal.valueOf(14.30));
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("Kabel RGB Wit 1 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("PVCopenend");
        cable.setCode("R");
        part = new Part(cable);
        part.setId("60005");
        part.setPrice(BigDecimal.valueOf(1.64));
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("Kabel RGB Wit 5 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("PVCopenend");
        cable.setCode("S");
        part = new Part(cable);
        part.setId("60005s");
        part.setPrice(BigDecimal.valueOf(8.2));
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Kabel RGB Wit 10 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("PVCopenend");
        cable.setCode("T");
        part = new Part(cable);
        part.setId("60005t");
        part.setPrice(BigDecimal.valueOf(16.4));
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Kabel Mono PUR 1 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("PVCopenend");
        cable.setCode("D");
        part = new Part(cable);
        part.setId("60006");
        part.setPrice(BigDecimal.valueOf(4.13));
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Kabel RGB PUR 1 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("RGB");
        cable.setCode("G");
        part = new Part(cable);
        part.setId("60007");
        part.setPrice(BigDecimal.valueOf(4.77));
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("Kabel Mono PUR 1 m with PU connector set");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("PU");
        cable.setCode("U");
        part = new Part(cable);
        part.setId("60010");
        part.setPrice(BigDecimal.valueOf(5.42));
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("Kabel Mono PUR 4 m with PU connector set");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("PU");
        cable.setCode("V");
        part = new Part(cable);
        part.setId("60011");
        part.setPrice(BigDecimal.valueOf(8.92));
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("Kabel Mono PUR 10 m with PU connector set");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("PU");
        cable.setCode("W");
        part = new Part(cable);
        part.setId("60012");
        part.setPrice(BigDecimal.valueOf(13.29));
        parts.add(part);
        //60012	Kabel Mono PUR 10 m with PU connector set	W	€ 13,29
        cable = new Cable(new Dimension(null));
        cable.setName("Kabel RGB PUR 1 m with PU connector set");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("PU");
        cable.setCode("X");
        part = new Part(cable);
        part.setId("60013");
        part.setPrice(BigDecimal.valueOf(6.63));
        parts.add(part);
        //60013	Kabel RGB PUR 1 m with PU connector set	X	€ 6,63
        cable = new Cable(new Dimension(null));
        cable.setName("Kabel RGB PUR 5 m with PU connector set");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("PU");
        cable.setCode("Y");
        part = new Part(cable);
        part.setId("60014");
        part.setPrice(BigDecimal.valueOf(10.41));
        parts.add(part);
        //60014	Kabel RGB PUR 5 m with PU connector set	Y	€ 10,41
        cable = new Cable(new Dimension(null));
        cable.setName("Kabel RGB PUR 10 m with PU connector set");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("PU");
        cable.setCode("Z");
        part = new Part(cable);
        part.setId("60015");
        part.setPrice(BigDecimal.valueOf(15.68));
        parts.add(part);
        //60015	Kabel RGB PUR 10 m with PU connector set	Z	€ 15,68


        //(GABHPQRSTUV){1}
        Covering noCovering = new NoCovering();
        noCovering.setName("Geen kap");
        noCovering.setCode("E");
        noCovering.setStep(2);
        relationDefinitionL20.addModel(noCovering);

        part = new NotExistingPart(noCovering);
        part.setId("co1");
        parts.add(part);

        ResinClear resinClear = new ResinClear(null);
        resinClear.setName("Helder");
        resinClear.setCode("C");
        resinClear.setStep(2);
        models.add(resinClear);
        relationDefinitionL20.addModel(resinClear);

        part = new NotExistingPart(resinClear);
        part.setId("coh1");
        parts.add(part);

        part = new Part(resinClear);
        part.setId("95000");
        part.setDescription("Ingieten liniLED L20 Helder");
        part.setPrice(BigDecimal.valueOf(43.13
        ));
        part.setType("MTR");
        parts.add(part);
        profileL20.getRelation().addRelateTo(part, null);
        //        "95000","Ingieten liniLED L20 Helder","11.50","MTR","","10"

        ResinDiffuse resinDiffuse = new ResinDiffuse(null);
        resinDiffuse.setName("Diffuus");
        resinDiffuse.setCode("D");
        models.add(resinDiffuse);
        relationDefinitionL20.addModel(resinDiffuse);
        part = new NotExistingPart(resinDiffuse);
        part.setId("cod1");
        parts.add(part);

        part = new Part(resinDiffuse);
        part.setId("95001");
        part.setPrice(BigDecimal.valueOf(43.13
        ));
        part.setDescription("Ingieten liniLED L20 Diffuus");
        part.setType("MTR");
        parts.add(part);
        profileL20.getRelation().addRelateTo(part, null);

//        "95001","Ingieten liniLED L20 Diffuus","11.50","MTR","","10"


        part = new Part(resinClear);
        part.setDescription("Ingieten liniLED L30 Helder");
        part.setPrice(BigDecimal.valueOf(86.25));
        part.setType("MTR");
        part.setId("95003");
        parts.add(part);
        profileL30.getRelation().addRelateTo(part, null);
        //        "95003","Ingieten liniLED L30 Helder","17.25","MTR","","10"


        part = new Part(resinDiffuse);
        part.setDescription("Ingieten liniLED L30 Diffuus");
        part.setId("95004");
        part.setPrice(BigDecimal.valueOf(86.25));
        part.setType("MTR");
        parts.add(part);
        profileL30.getRelation().addRelateTo(part, null);
        //        "95004","Ingieten liniLED L30 Diffuus","17.25","MTR","","10"


        part = new Part(resinClear);
        part.setPrice(BigDecimal.valueOf(23));
        part.setId("95010");
        part.setDescription("Ingieten liniLED H20 Helder");
        part.setType("MTR");
        part.setPrice(BigDecimal.valueOf(86.25
        ));
        parts.add(part);
        profileH20.getRelation().addRelateTo(part, null);
//        "95010","Ingieten liniLED H20 Helder","23.00","MTR","","10"


        part = new Part(resinDiffuse);
        part.setPrice(BigDecimal.valueOf(23));
        part.setId("95011");
        part.setDescription("Ingieten liniLED H20 Diffuus");
        part.setType("MTR");
        part.setPrice(BigDecimal.valueOf(86.25
        ));
        parts.add(part);
        profileH20.getRelation().addRelateTo(part, null);
        //        "95011","Ingieten liniLED H20 Diffuus","23.00","MTR","","10"


        part = new Part(resinClear);
        part.setPrice(BigDecimal.valueOf(174.37));
        part.setId("95013");
        part.setDescription("Ingieten liniLED H30 Helder");
        part.setType("MTR");
        parts.add(part);
        profileH30.getRelation().addRelateTo(part, null);
//        "95013","Ingieten liniLED H30 Helder","46.50","MTR","","10"

        part = new Part(resinDiffuse);
        part.setPrice(BigDecimal.valueOf(174.37));
        part.setId("95014");
        part.setDescription("Ingieten liniLED H30 Diffuus");
        part.setType("MTR");
        parts.add(part);
        profileH30.getRelation().addRelateTo(part, null);
//        "95014","Ingieten liniLED H30 Diffuus","46.50","MTR","","10"


        LedStrip ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Deco rood");
        ledStrip.setMaxDimension(new Dimension(2600));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(LedStrip.COLOR_TYPE).setValue("rood");
        ledStrip.setCode("DR");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(36.96
        ));
        part.setId("12182");
        part.setDescription("liniLED PCB Rood D");
        part.setType("MTR");
        parts.add(part);

        ledStrip = new RGBLedStrip(new Dimension(null));
        ledStrip.setName("liniLED RGB Deco");
        ledStrip.setMaxDimension(new Dimension(2600));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(LedStrip.COLOR_TYPE).setValue("rgb");
        ledStrip.setCode("RP");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(69.52
        ));
        part.setId("12187");
        part.setType("MTR");
        part.setDescription("liniLED PCB RGB D");
        parts.add(part);
        //"21002","liniLED PCB RGB D","15.00","MTR","","10"

        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Deco groen");
        ledStrip.setMaxDimension(new Dimension(2600));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(LedStrip.COLOR_TYPE).setValue("groen");
        ledStrip.setCode("DG");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(48.40
        ));
        part.setId("12183");
        part.setType("MTR");
        part.setDescription("liniLED PCB Groen D");
        parts.add(part);
        //"21005","liniLED PCB Groen D","10.50","MTR","","10"

        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Deco blauw");
        ledStrip.setMaxDimension(new Dimension(2600));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(LedStrip.COLOR_TYPE).setValue("blauw");
        ledStrip.setCode("DB");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(48.4));
        part.setId("21006");
        part.setType("MTR");
        part.setDescription("liniLED PCB Blauw D");
        parts.add(part);
        //"21006","liniLED PCB Blauw D","10.50","MTR","","10"


        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Deco amber");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(LedStrip.COLOR_TYPE).setValue("amber");
        ledStrip.setCode("DA");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(36.96
        ));
        part.setId("12181");
        part.setType("MTR");
        part.setDescription("liniLED PCB Amber D");
        parts.add(part);

        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB NW 3000K D");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(LedStrip.COLOR_TYPE).setValue("white");
        ledStrip.getProperty(PowerLedStrip.KELVIN_TYPE).setValue("3000");
        ledStrip.setCode("DW");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(51.04
        ));
        part.setId("12141");
        part.setType("MTR");
        part.setDescription("liniLED PCB NW 3000K D");
        parts.add(part);


        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB NW 4000K D");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(LedStrip.COLOR_TYPE).setValue("white");
        ledStrip.getProperty(PowerLedStrip.KELVIN_TYPE).setValue("4000");
        ledStrip.setCode("DN");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(51.04
        ));
        part.setId("12142");
        part.setType("MTR");
        part.setDescription("liniLED PCB NW 3000K D");
        parts.add(part);

        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB KW 6500K D");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(LedStrip.COLOR_TYPE).setValue("white");
        ledStrip.getProperty(PowerLedStrip.KELVIN_TYPE).setValue("6500");
        ledStrip.setCode("DC");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(51.04
        ));
        part.setId("12143");
        part.setType("MTR");
        part.setDescription("liniLED PCB KW 6500K D");
        parts.add(part);


        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Power RGB 240");
        ledStrip.setMaxDimension(new Dimension(2600));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.setCode("RQ");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(77.95
        ));
        part.setId("12139");
        part.setType("MTR");
        part.setDescription("liniLED PCB RGB 240 P");
        parts.add(part);

        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Power Orange");
        ledStrip.setMaxDimension(new Dimension(2600));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.setCode("PO");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(69.52));
        part.setId("12175");
        part.setType("MTR");
        part.setDescription("liniLED PCB Oranje P");
        parts.add(part);


        //"21017","liniLED PCB RGB P","38.60","MTR","","10"


        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Power rood");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(PowerLedStrip.COLOR_TYPE).setValue("rood");
        ledStrip.setCode("PR");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(69.52
        ));
        part.setId("12176");
        part.setType("MTR");
        part.setDescription("liniLED PCB Rood P");
        parts.add(part);

        //"21018","liniLED PCB Rood P","16.00","MTR","","10"


        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Power groen");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(PowerLedStrip.COLOR_TYPE).setValue("groen");
        ledStrip.setCode("PG");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(69.52));
        part.setId("12178");
        part.setType("MTR");
        part.setDescription("liniLED PCB Groen P");
        parts.add(part);

        //"21020","liniLED PCB Groen P","19.00","MTR","","10"


        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Power blauw");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.setCode("PB");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(69.52
        ));
        part.setId("12177");
        part.setType("MTR");
        part.setDescription("liniLED PCB Blauw P");
        parts.add(part);
        //"21019","liniLED PCB Blauw P","19.00","MTR","","10"

        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED Power amber");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.setCode("PA");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(69.52));
        part.setId("12179");
        part.setType("MTR");
        part.setDescription("liniLED PCB amber P");
        parts.add(part);

        //"21021","liniLED PCB Amber","16.00","MTR","","10"


        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB EWW 2700K P");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("PE");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(63.36));
        part.setId("12188");
        part.setType("MTR");
        part.setDescription("liniLED PCB EWW 2700K P");
        parts.add(part);

        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB WW 3000K P");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("3000");
        ledStrip.setCode("PW");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(63.36));
        part.setId("12189");
        part.setType("MTR");
        part.setDescription("liniLED PCB WW 3000K P");
        parts.add(part);

        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB NW 4000K P");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("4000");
        ledStrip.setCode("PN");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(63.36));
        part.setId("12190");
        part.setType("MTR");
        part.setDescription("liniLED PCB NW 4000K P");
        parts.add(part);
        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB KW 6500K P");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("6500");
        ledStrip.setCode("PC");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(63.36));
        part.setId("12191");
        part.setType("MTR");
        part.setDescription("liniLED PCB KW 6500K P");
        parts.add(part);


        ledStrip = new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED HP 2400K UWW (PSP)");
        ledStrip.setMaxDimension(new Dimension(2670));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2400");
        ledStrip.setCode("HU");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12199");
        part.setType("MTR");
        part.setDescription("liniLED PCB EWW 2700K HP (PSP)");
        parts.add(part);


        ledStrip = new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED HP 2700K EWW");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("HE");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12192");
        part.setType("MTR");
        part.setDescription("liniLED PCB EWW 2700K HP (PSP)");
        parts.add(part);


        ledStrip = new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED HP 3000K WW");
        ledStrip.setMaxDimension(new Dimension(2670));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("3000");
        ledStrip.setCode("HW");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12193");
        part.setType("MTR");
        part.setDescription("liniLED PCB WW 3000K HP (PSP)");
        parts.add(part);

        ledStrip = new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED HP 4000K WW");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("4000");
        ledStrip.setCode("HN");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(19));
        part.setId("12194");
        part.setType("MTR");
        part.setDescription("liniLED PCB NW 4000K HP (PSP)");
        parts.add(part);

        ledStrip = new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED HP 6500K KW");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("6500");
        ledStrip.setCode("HC");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(19));
        part.setId("12195");
        part.setType("MTR");
        part.setDescription("liniLED PCB KW 6500K HP (PSP)");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB FW 2000K Photon");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2000");
        ledStrip.setCode("NF");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12147");
        part.setType("MTR");
        part.setDescription("liniLED PCB FW 2000K Photon 1200");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB UWW 2400K Photon");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2400");
        ledStrip.setCode("NU");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12148");
        part.setType("MTR");
        part.setDescription("liniLED PCB UWW 2400K Photon 1200");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB EWW 2700K Photon 1200");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("NE");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12149");
        part.setType("MTR");
        part.setDescription("liniLED PCB EWW 2700K Photon 1200");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB WW 3000K Photon 1200");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("3000");
        ledStrip.setCode("NW");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12150");
        part.setType("MTR");
        part.setDescription("liniLED PCB WW 3000K Photon 1200");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB NW 4000K Photon 1200");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("4000");
        ledStrip.setCode("NN");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12151");
        part.setType("MTR");
        part.setDescription("liniLED PCB NW 4000K Photon 1200");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB KW 6500K Photon 1200");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("6500");
        ledStrip.setCode("NC");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12152");
        part.setType("MTR");
        part.setDescription("liniLED PCB KW 6500K Photon 1200");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB FW 2000K Photon 1700");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("6500");
        ledStrip.setCode("QF");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(130.56));
        part.setId("12153");
        part.setType("MTR");
        part.setDescription("liniLED PCB FW 2000K Photon 1700");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB UWW 2400K Photon 1700");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2400");
        ledStrip.setCode("QU");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(130.56));
        part.setId("12154");
        part.setType("MTR");
        part.setDescription("liniLED PCB UWW 2400K Photon 1700");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB EWW 2700K Photon 1700");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("QE");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(130.56));
        part.setId("12155");
        part.setType("MTR");
        part.setDescription("liniLED PCB UWW 2400K Photon 1700");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB WW 3000K Photon 1700");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("3000");
        ledStrip.setCode("QW");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(130.56));
        part.setId("12156");
        part.setType("MTR");
        part.setDescription("liniLED PCB WW 3000K Photon 1700");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB NW 4000K Photon 1700");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("4000");
        ledStrip.setCode("QN");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(130.56));
        part.setId("12157");
        part.setType("MTR");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB KW 6500K Photon 1700");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("4000");
        ledStrip.setCode("QC");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(130.56));
        part.setId("12158");
        part.setType("MTR");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB FW 2000K Photon 2500");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2000");
        ledStrip.setCode("WF");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(130.56));
        part.setId("12159");
        part.setType("MTR");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB UWW 2400K Photon 2500");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2400");
        ledStrip.setCode("WU");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(146.48));
        part.setId("12160");
        part.setType("MTR");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB EWW 2700K Photon 2500");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("WE");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(146.48));
        part.setId("12161");
        part.setType("MTR");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB WW 3000K Photon 2500");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("3000");
        ledStrip.setCode("WW");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(146.48));
        part.setId("12162");
        part.setType("MTR");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB NW 4000K Photon 2500");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("4000");
        ledStrip.setCode("WN");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(146.48));
        part.setId("12163");
        part.setType("MTR");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB KW 6500K Photon 2500");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("6500");
        ledStrip.setCode("WC");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(146.48));
        part.setId("12164");
        part.setType("MTR");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB FW 2000K Photon 3300");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2000");
        ledStrip.setCode("XF");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(179.38));
        part.setId("12165");
        part.setType("MTR");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB UWW 2400K Photon 3300");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2400");
        ledStrip.setCode("XU");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(179.38));
        part.setId("12166");
        part.setType("MTR");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB EWW 2700K Photon 3300");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2400");
        ledStrip.setCode("XE");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(179.38));
        part.setId("12167");
        part.setType("MTR");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB WW 3000K Photon 3300");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("3000");
        ledStrip.setCode("XW");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(179.38));
        part.setId("12168");
        part.setType("MTR");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB NW 4000K Photon 3300");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("4000");
        ledStrip.setCode("XN");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(179.38));
        part.setId("12169");
        part.setType("MTR");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB KW 6500K Photon 3300");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("6500");
        ledStrip.setCode("XC");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(179.38));
        part.setId("12170");
        part.setType("MTR");
        parts.add(part);


        ledStrip = new TunnableLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB TW 2700K-6500K 1700");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("QZ");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(152.49));
        part.setId("12144");
        part.setType("MTR");
        parts.add(part);

        ledStrip = new TunnableLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB TW 2700K-6500K 2500");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("WZ");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(195.83));
        part.setId("12145");
        part.setType("MTR");
        parts.add(part);

        ledStrip = new TunnableLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB TW 2700K-6500K 3300");
        ledStrip.setMaxDimension(new Dimension(2100));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("XZ");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(219.73
        ));
        part.setId("12146");
        part.setType("MTR");
        parts.add(part);


        ledStrip = new TunnableLedStrip(new Dimension(null));
        ledStrip.setName("liniLED PCB TW 2700K-6500K 1200");
        ledStrip.setMaxDimension(new Dimension(2100));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("NZ");
        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(139.70));
        part.setId("12173");
        part.setType("MTR");
        parts.add(part);


        part = new Part(null);
        part.setPrice(BigDecimal.valueOf(4.71));
        part.setId("60000");
        part.setType("ST");
        part.setDescription("Dubbelz Tape 6mm tbv PCB (l=50m)");
        parts.add(part);


        Clip clip = new Clip();
        clip.setName("liniLED Aeris Clip 20");

        part = new Part(clip);
        part.setId("10890");
        part.setPrice(BigDecimal.valueOf(1.86
        ));
        part.setDescription("Clip 20");
        parts.add(part);

        clip = new Clip();
        clip.setName("liniLED Aeris Clip 30");

        part = new Part(clip);
        part.setId("10891");
        part.setPrice(BigDecimal.valueOf(1.86
        ));
        part.setDescription("Clip 30");
        parts.add(part);

        clip = new Clip();
        clip.setName("Accessoire: Clip");
        clip.setCode("a");
        part = new NotExistingPart(clip);
        part.setId("clip");
        part.setDescription("Clip.");
        parts.add(part);

        Part arbeid = new Part();
        arbeid.setId("ARBEID");
        arbeid.setDescription("Arbeidsminuut intern magazijn");
        arbeid.setPrice(BigDecimal.valueOf(0.59));
        arbeid.setType("MINUUT");
        parts.add(arbeid);

        Part tule = new Part();
        tule.setId("GRIJP_KTULE_EINDKAP");
        tule.setDescription("Kabeltule t.b.v. eindkap");
        tule.setPrice(BigDecimal.valueOf(0.07));
        tule.setType("ST");
        parts.add(tule);

        tule = new Part();
        tule.setId("GRIJP_KTULE_ALU_ZWART");
        tule.setDescription("Kabeltule t.b.v. ALU zwart");
        tule.setPrice(BigDecimal.valueOf(0.07));
        tule.setType("ST");
        parts.add(tule);

        tule = new Part();
        tule.setId("GRIJP_KTULE_ALU_WIT");
        tule.setDescription("Kabeltule t.b.v. ALU wit");
        tule.setPrice(BigDecimal.valueOf(0.11));
        tule.setType("ST");
        parts.add(tule);

        Part sticker = new Part();
        sticker.setId("GRIJP_PSTICKER");
        sticker.setDescription("Productsticker");
        sticker.setPrice(BigDecimal.valueOf(0.25));
        sticker.setType("ST");
        parts.add(sticker);

        Part handleiding = new Part();
        handleiding.setId("HANDLEIDING");
        handleiding.setDescription("Handleiding");
        handleiding.setPrice(BigDecimal.valueOf(0.70));
        handleiding.setType("ST");
        parts.add(handleiding);

        Part verpakking = new Part();
        verpakking.setId("VERPAKKING");
        verpakking.setDescription("Verpakking");
        verpakking.setPrice(BigDecimal.valueOf(0.50));
        verpakking.setType("ST");
        parts.add(verpakking);

        composedProduct = new ComposedProduct(null, null);
        composedProduct.setName("Product lengte");
        part = new Part(composedProduct);
        part.setId("comp");
        parts.add(part);


    }
    private RelationDefinition createRelationDefinition() {
        RelationDefinition relationDefinition = new RelationDefinition();
        relationDefinitions.add(relationDefinition);
        return relationDefinition;
    }
    private RelationDefinition createRelationDefinition(RelationState state,String message) {
        RelationDefinition relationDefinition = new RelationDefinition(state,message);
        relationDefinitions.add(relationDefinition);
        return relationDefinition;
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
        int startPositionCode = 0;
        Accumulator accumulator = new Accumulator(startPositionCode);
        steps.add(new ParserStepRealModel(1, true, accumulator.start(), accumulator.plus(Profile.CODE_LENGTH), Profile.class, "", "Profiel niet geconfigureerd"));
        steps.add(new ParserStepRealModel(2, true, accumulator.start(), accumulator.plus(Covering.CODE_LENGTH), Covering.class, "", "Behuizing niet geconfigureerd"));
        steps.add(new ParserStepRealModel(3, true, accumulator.start(), accumulator.plus(2), LedStrip.class, "", "Kleur niet geconfigureerd"));
        steps.add(new ParserStepRealModel(4, true, accumulator.start(), accumulator.plus(1), Cable.class, "", "Kabel niet geconfigureerd"));
        steps.add(new ParserStepModel(5, true, accumulator.start(), accumulator.plus(1), CableEntry.class, "", "Kabeluiteinde niet geconfigureerd"));
        steps.add(new ParserStepModel(6, true, accumulator.start(), accumulator.plus(1), Mounting.class, "", "Montage opties niet geconfigureerd"));
        steps.add(new ParserStepDimensionModel(7, true, accumulator.start(), accumulator.plus(4), LedStrip.class, "", "Led strip lengte niet geconfigureerd", models));

        steps.add(new ParserStepRealModelComposed(8, ComposedProduct.class, "", "Productlengte automatisch berekend.", accumulator.start(), accumulator.plus(4), models));
        steps.add(new ParserStepModelRightParse(9, false, 0, 1, Accessoire.class, "", "Accessoire opties niet geconfigureerd"));

        for (ParseStep step : steps) {
            ModelResult modelResult = step.create(productcode, parts);

            if (modelResult != null && modelResult.getModel() != null) {
                models.add(modelResult.getModel());
            } else {

                models.add(new ErrorModel());
            }
            step.addModelResult(modelResult);
            composedProduct.addModelResult(modelResult);

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

    public List<Model> getModels() {
       return this.models;
    }

    public List<RelationDefinition> getRelationDefinitions() {
        return relationDefinitions;
    }
}
