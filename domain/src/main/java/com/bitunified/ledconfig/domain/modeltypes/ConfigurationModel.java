package com.bitunified.ledconfig.domain.modeltypes;

import com.bitunified.ledconfig.domain.Margin;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.product.cable.cableconfig.CableEntry;
import com.bitunified.ledconfig.domain.product.mounting.Mounting;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({CableEntry.class, Mounting.class})
public class ConfigurationModel extends Model {


    private Margin margins=new Margin();

    public Margin getMargins() {
        return margins;
    }

    public void setMargins(Margin margins) {
        this.margins = margins;
    }
}
