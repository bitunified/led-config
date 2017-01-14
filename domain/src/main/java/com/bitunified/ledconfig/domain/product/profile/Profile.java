package com.bitunified.ledconfig.domain.product.profile;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;

import javax.xml.bind.annotation.XmlRootElement;


public class Profile extends RealModel {


    private Dimension lengthForCasting;
    private String lengthForCastingStr;
    public Profile(){

    }
    public Profile(Dimension dimension) {
        super(dimension);

    }


    public void setLengthForCasting(Dimension lengthForCasting) {
        this.lengthForCasting = lengthForCasting;
    }

    public Dimension getLengthForCasting() {
        return lengthForCasting;
    }

    public String getLengthForCastingStr() {
        return lengthForCastingStr;
    }

    public void setLengthForCastingStr(String lengthForCastingStr) {
        this.lengthForCastingStr = lengthForCastingStr;
    }
}
