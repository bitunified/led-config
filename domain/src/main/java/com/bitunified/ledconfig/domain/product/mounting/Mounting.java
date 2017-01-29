package com.bitunified.ledconfig.domain.product.mounting;


import com.bitunified.ledconfig.domain.modeltypes.ConfigurationModel;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({EndCapBothSidesMounting.class,
        EndCapLeftMounting.class,
        EndCapRightMounting.class,
        HighEndCapRightMounting.class,
        HighEndCapBothCableChannelMounting.class,
        HighEndCapBothMounting.class,
        HighEndCapLeftMounting.class,
        NoEndCapsMounting.class})
public class Mounting extends ConfigurationModel {


}
