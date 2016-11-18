package com.bitunified.ledconfig.parts;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.product.profile.Profile;

public class Part {
    private Model product;
    double price;
    private String description;

    public Part(){
        
    }
    public Part(Model product) {
        this.product=product;
    }
}
