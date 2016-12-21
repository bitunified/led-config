package com.bitunified.ledconfig.domain.modeltypes;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RealModel extends Model {
    private Dimension dimension=new Dimension(null,null);

    private Dimension maxDimension;

    public RealModel(){

    }
    public RealModel(Dimension dimension) {
        this.dimension=dimension;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Dimension getMaxDimension() {
        return maxDimension;
    }
    public void setDimension(Dimension dimension){
        this.dimension=dimension;
    }

    public void setMaxDimension(Dimension maxDimension) {
        this.maxDimension = maxDimension;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
