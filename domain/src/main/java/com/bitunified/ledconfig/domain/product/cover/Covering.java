package com.bitunified.ledconfig.domain.product.cover;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.domain.product.cover.types.Cover;
import com.bitunified.ledconfig.domain.product.cover.types.Resin;
import com.bitunified.ledconfig.domain.product.cover.types.ResinClear;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({Resin.class,Cover.class})
public class Covering extends RealModel{
    public static final Property TRANSLUCENCY = new Property("translucency","");
    public static final int CODE_LENGTH = 1;

    public Covering(Dimension dimension){
        super(dimension);
        addProperty(TRANSLUCENCY);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
