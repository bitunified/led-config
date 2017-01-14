package com.bitunified.ledconfig.domain.product.cover.types;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.product.cover.Covering;

import javax.xml.bind.annotation.XmlSeeAlso;


public class ResinClear extends Resin {

    public ResinClear(Dimension dimension) {
        super(dimension);
        TRANSLUCENCY.setValue("CLEAR");
    }
}
