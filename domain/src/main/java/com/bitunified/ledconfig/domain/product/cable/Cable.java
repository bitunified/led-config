package com.bitunified.ledconfig.domain.product.cable;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;

public class Cable extends RealModel {

    public static final String CABLE_TYPE = "cable_type";

    public static final Property TYPE=new Property(CABLE_TYPE,"");
    public Cable(Dimension dimension) {
        super(dimension);
        addProperty(TYPE);

    }
}
