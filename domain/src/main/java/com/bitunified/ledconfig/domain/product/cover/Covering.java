package com.bitunified.ledconfig.domain.product.cover;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;

public class Covering extends RealModel{
    public static final Property TRANSLUCENCY = new Property("translucency","");

    public Covering(Dimension dimension){
        super(dimension);
        addProperty(TRANSLUCENCY);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
