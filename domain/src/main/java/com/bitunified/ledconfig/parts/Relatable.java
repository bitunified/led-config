package com.bitunified.ledconfig.parts;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.Orientation;
import com.bitunified.ledconfig.domain.Relation;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({Model.class,Part.class})
public class Relatable {

    private Relation relates=new Relation();

    private Orientation orientation;

    public Relation getRelation(){
        return relates;
    }

    public void setRelates(Relation relatedTo) {
        this.relates = relatedTo;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }


}
