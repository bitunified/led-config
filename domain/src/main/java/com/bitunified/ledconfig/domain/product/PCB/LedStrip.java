package com.bitunified.ledconfig.domain.product.PCB;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;

public class LedStrip extends RealModel {


    public static final String SECTION_WIDTH = "section";

    public static final Property SECTION=new Property(SECTION_WIDTH,"");
    public LedStrip(Dimension dimension) {
        super(dimension);
        addProperty(SECTION);

    }
}
