package com.bitunified.ledconfig.parts;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.domain.product.profile.Profile;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

public class Part implements Serializable{
    private RealModel product;
    private Model configModel;
    private BigDecimal price;
    private String code;
    private String description;
    private String id;

    public Part(Model configModel){
        this.configModel=configModel;
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
     return ToStringBuilder.reflectionToString(this);
    }

    public Model getConfigModel() {
        return configModel;
    }

    public void setConfigModel(Model configModel) {
        this.configModel = configModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        return id != null ? id.equals(part.id) : part.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
