package com.bitunified.ledconfig.domain.product.cable;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;

import javax.xml.bind.annotation.XmlRootElement;

public class Cable extends RealModel {

    public Cable(){

    }
    public static final String CABLE_TYPE = "cable_type";
    public static final String CABLE_END = "cable_end";

    public static final Property TYPE=new Property(CABLE_TYPE,"");
    public static final Property END=new Property(CABLE_END,"");
    public Cable(Dimension dimension) {
        super(dimension);
        addProperty(TYPE);
        addProperty(END);
    }
}
