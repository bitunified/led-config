package com.bitunified.ledconfig.composedproduct;

import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.domain.product.ModelResult;
import com.bitunified.ledconfig.parts.Part;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlMixed;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ComposedProduct extends RealModel {

    public ComposedProduct() {

    }

    @XmlMixed
    private List<Part> products = new ArrayList<Part>();

    @JsonIgnore
    private Set<ModelResult> modelResults = new HashSet<ModelResult>();

    public ComposedProduct(Integer totalWidth, Integer totalHeight) {
        this.getDimension().setWidth(totalWidth);
        this.getDimension().setHeight(totalHeight);
    }

    public void addProduct(Part model) {
        products.add(model);
    }

    public List<Part> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public void addModelResult(ModelResult modelResult) {
        this.modelResults.add(modelResult);
    }

    public Set<ModelResult> getModelResults() {
        return modelResults;
    }

    public void setModelResults(Set<ModelResult> modelResults) {
        this.modelResults = modelResults;
    }
}
