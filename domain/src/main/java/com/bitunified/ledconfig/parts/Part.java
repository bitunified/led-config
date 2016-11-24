package com.bitunified.ledconfig.parts;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.product.profile.Profile;

import java.math.BigDecimal;

public class Part {
    private Model product;
    private BigDecimal price;
    private String code;
    private String description;

    public Part(){
        
    }
    public Part(Model product) {
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

    public Model getProduct() {
        return product;
    }
}
