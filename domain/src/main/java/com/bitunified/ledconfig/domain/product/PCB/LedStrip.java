package com.bitunified.ledconfig.domain.product.PCB;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.domain.product.PCB.types.*;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({HighPowerLedStrip.class, PhotonLedStrip.class, DecoLedStrip.class, PowerLedStrip.class, RGBLedStrip.class,RGBLedStrip.class})
public class LedStrip extends RealModel {

    public static final String COLOR_TYPE = "color";
    public static final Property COLOR=new Property(COLOR_TYPE,"");

    public LedStrip(){

    }

    public static final String SECTION_WIDTH = "section";

    public static final Property SECTION=new Property(SECTION_WIDTH,"");
    public LedStrip(Dimension dimension) {
        super(dimension);
        addProperty(SECTION);

    }
}
