package com.bitunified.ledconfig.domain.product.mounting;


import com.bitunified.ledconfig.domain.modeltypes.ConfigurationModel;
import com.bitunified.ledconfig.domain.product.cable.cableconfig.CableEntry;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({EndCapBothSidesMounting.class,
        EndCapLeftMounting.class,
        EndCapRightMounting.class,
        HighEndCapRightMounting.class,
        HighEndCapBothCableChannelMounting.class,
        HighEndCapBothMounting.class,
        HighEndCapLeftMounting.class,
        NoEndCapsMounting.class})
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "typeClass")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EndCapBothSidesMounting.class),
        @JsonSubTypes.Type(value = EndCapLeftMounting.class)
})
public class Mounting extends ConfigurationModel {


    public Mounting(){
        super();
        setStep(6);
    }

}
