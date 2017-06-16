package com.bitunified.ledconfig.domain.product.cable;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;

import javax.xml.bind.annotation.XmlRootElement;

public class Cable extends RealModel {

    public Cable(){

    }
    public static final String CABLE_TYPE_S = "cable_type";
    public static final String CABLE_END_S = "cable_end";

    public  final Property CABLE_TYPE=new Property(CABLE_TYPE_S,"");
    public  final Property CABLE_END=new Property(CABLE_END_S,"");
    public Cable(Dimension dimension) {
        super(dimension);
        setStep(4);
        addProperty(CABLE_TYPE);
        addProperty(CABLE_END);
    }
}
