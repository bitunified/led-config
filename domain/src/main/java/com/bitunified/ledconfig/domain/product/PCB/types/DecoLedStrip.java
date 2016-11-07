package com.bitunified.ledconfig.domain.product.PCB.types;

import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;


public class DecoLedStrip extends LedStrip {
    public static final String COLOR = "color";
    public static final Property COLOR_PROP=new Property(COLOR,"");

    public DecoLedStrip(Dimension dimension){
        super(dimension);
        addProperty(COLOR_PROP);
    }
}
