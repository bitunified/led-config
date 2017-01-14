package com.bitunified.ledconfig.composedproduct;

import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.domain.product.ModelResult;
import com.bitunified.ledconfig.parts.Part;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.*;


public class ComposedProduct extends RealModel {

    public ComposedProduct(){

    }
    @XmlMixed
    private List<Part> products=new ArrayList<Part>();

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
    public List<Part> getProducts() {
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
