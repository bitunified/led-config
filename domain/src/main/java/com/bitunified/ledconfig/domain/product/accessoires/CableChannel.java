package com.bitunified.ledconfig.domain.product.accessoires;


import com.bitunified.ledconfig.domain.Dimension;

public class CableChannel extends Accessoire {
    public CableChannel(){

    }
    public CableChannel(Dimension dimension) {
        super();
        this.setDimension(dimension);
    }
}
