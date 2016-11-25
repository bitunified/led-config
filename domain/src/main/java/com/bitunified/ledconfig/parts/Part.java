package com.bitunified.ledconfig.parts;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.domain.product.profile.Profile;

import java.math.BigDecimal;

public class Part {
    private RealModel product;
    private BigDecimal price;
    private String code;
    private String description;

    public Part(){
        
    }
    public Part(RealModel product) {
        this.product=product;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public RealModel getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Product:"+product.toString()+", Code: "+code+ " Description: "+description;

    }
}
