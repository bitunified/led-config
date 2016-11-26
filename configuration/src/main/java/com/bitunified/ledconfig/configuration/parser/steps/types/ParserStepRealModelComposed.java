package com.bitunified.ledconfig.configuration.parser.steps.types;


import com.bitunified.ledconfig.configuration.parser.steps.ParseStep;
import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.parts.Part;

import java.util.List;

public class ParserStepRealModelComposed extends ParseStepBase implements ParseStep{


    private final Integer dataBegin;
    private final Integer dataEnd;
    private String errorMessage;

    public ParserStepRealModelComposed( Class<? extends Model> model, String regex, String errorMessage, Integer dataBegin, Integer dataEnd) {
    super(model,regex,errorMessage);

        this.dataBegin = dataBegin;
        this.dataEnd = dataEnd;
    }

    public Model create(String productcode, List<Part> parts) {

        for (Part part : parts) {
            if (part.getProduct().getClass().getName().equals(modelClass.getName())) {
                RealModel product = part.getProduct();


                        if (dataEnd != null) {
                            String length = productcode.substring(dataBegin, dataEnd);
                            Integer lengthNumber=null;
                            try {
                                lengthNumber = Integer.parseInt(length);
                            }catch (NumberFormatException e) {

                            }
                            if (lengthNumber!=null) {
                                product.setDimension(new Dimension(new Integer(length)));

                            }


                        }

                        return product;
                    }





        }
        return null;
    }


    @Override
    public boolean isOptional() {
        return true;
    }
}
