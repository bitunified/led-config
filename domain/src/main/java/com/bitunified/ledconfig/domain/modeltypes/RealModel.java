package com.bitunified.ledconfig.domain.modeltypes;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Margin;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.description.Descriptive;

public class RealModel extends Model {
    private Dimension dimension;

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

    public void setMaxDimension(Dimension maxDimension) {
        this.maxDimension = maxDimension;
    }

    @Override
    public String toString() {
        return "["+super.toString()+" MaxDim: "+this.maxDimension+"]";
    }
}
