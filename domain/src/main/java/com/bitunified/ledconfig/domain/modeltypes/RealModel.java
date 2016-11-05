package com.bitunified.ledconfig.domain.modeltypes;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;

public class RealModel extends Model {
    private Dimension dimension;

    public RealModel(){

    }
    public RealModel(Dimension dimension) {
       this.dimension=dimension;
    }

    public Dimension getDimension() {
        return dimension;
    }
}
