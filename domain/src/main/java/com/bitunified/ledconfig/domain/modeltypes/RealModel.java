package com.bitunified.ledconfig.domain.modeltypes;


import com.bitunified.ledconfig.composedproduct.ComposedProduct;
import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Margin;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.Relation;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;
import com.bitunified.ledconfig.domain.product.accessoires.Accessoire;
import com.bitunified.ledconfig.domain.product.cable.Cable;
import com.bitunified.ledconfig.domain.product.mounting.EndCap;
import com.bitunified.ledconfig.domain.product.profile.Profile;
import com.bitunified.ledconfig.parts.Relatable;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({Profile.class, LedStrip.class, Cable.class, ComposedProduct.class, Accessoire.class, EndCap.class, Relatable.class})
public class RealModel extends Model {
    protected Dimension dimension = new Dimension(null, null);

    protected Dimension maxDimension;



    private Margin margin = new Margin();

    public RealModel() {

    }

    public RealModel(Dimension dimension) {
        this.dimension = dimension;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Dimension getMaxDimension() {
        return maxDimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setMaxDimension(Dimension maxDimension) {
        this.maxDimension = maxDimension;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public void setLeftSpace(String leftSpace) {
        if (this.margin == null) {
            this.margin = new Margin();
        }
        this.margin.setLeft(Integer.parseInt(leftSpace));
    }

    public void setLeftSpace(Integer leftSpace) {
        if (this.margin == null) {
            this.margin = new Margin();
        }
        this.margin.setLeft(leftSpace);
    }

    public Integer getLeftSpace() {
        return margin.getLeft();
    }

    public Integer getRightSpace() {
        return margin.getRight();
    }

    public void setRightSpace(Integer rightSpace) {
        this.margin.setRight(rightSpace);
    }

    public Margin getMargin() {
        return margin;
    }

    public void setMargin(Margin margin) {
        this.margin = margin;
    }


}
