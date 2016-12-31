package com.bitunified.ledconfig.domain.product.PCB.types;

import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;


public class PowerLedStrip extends LedStrip {
    public static final String KELVIN_TYPE = "kelvin";
    public static final Property KELVIN=new Property(KELVIN_TYPE,"");
    public static final String COLOR_TYPE = "color";
    public static final Property COLOR=new Property(COLOR_TYPE,"");


    public PowerLedStrip(Dimension dimension){
        super(dimension);
        addProperty(KELVIN);
        addProperty(COLOR);
    }
}
