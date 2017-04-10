package com.bitunified.ledconfig.domain.product.cover.types;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.product.cover.Covering;

public class Cap extends Covering {

    public Cap(){
        this(new Dimension());
    }
    public Cap(Dimension dimension) {
        super(dimension);
    }
}
