package com.bitunified.ledconfig.domain.modeltypes;

import com.bitunified.ledconfig.domain.Margin;
import com.bitunified.ledconfig.domain.Model;

public class ConfigurationModel extends Model {


    private Margin margins;

    public Margin getMargins() {
        return margins;
    }

    public void setMargins(Margin margins) {
        this.margins = margins;
    }
}
