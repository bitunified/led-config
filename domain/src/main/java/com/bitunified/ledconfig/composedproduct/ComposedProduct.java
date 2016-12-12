package com.bitunified.ledconfig.composedproduct;

import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.domain.product.ModelResult;
import com.bitunified.ledconfig.parts.Part;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class ComposedProduct extends RealModel {

    private Set<Part> products=new HashSet<Part>();

    private Set<ModelResult> modelResults=new HashSet<ModelResult>();

    public ComposedProduct(Integer totalWidth, Integer totalHeight) {
        this.getDimension().width=totalWidth;
                this.getDimension().height=totalHeight;
    }

    public void addProduct(Part model) {
        products.add(model);
    }
    public void addProducts(Collection<Part> models) {
        products.addAll(models);
    }
    public Set<Part> getProducts() {
        return products;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
    public void addModelResult(ModelResult modelResult){
        this.modelResults.add(modelResult);
    }
    public Set<ModelResult> getModelResults() {
        return modelResults;
    }

    public void setModelResults(Set<ModelResult> modelResults) {
        this.modelResults = modelResults;
    }
}
