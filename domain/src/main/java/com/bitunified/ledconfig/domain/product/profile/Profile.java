package com.bitunified.ledconfig.domain.product.profile;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;

public class Profile extends RealModel {


    private Dimension lengthForCasting;
    Profile(){

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
}
