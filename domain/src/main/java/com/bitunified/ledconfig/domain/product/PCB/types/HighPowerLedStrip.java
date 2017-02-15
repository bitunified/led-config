package com.bitunified.ledconfig.domain.product.PCB.types;

import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;


public class HighPowerLedStrip extends PowerLedStrip {

    public static final String KELVIN_TYPE = "kelvin";
    public final Property KELVIN=new Property(KELVIN_TYPE,"");

    public HighPowerLedStrip(){

    }
    public HighPowerLedStrip(Dimension dimension){
        super(dimension);

        addProperty(KELVIN);

    }
}
