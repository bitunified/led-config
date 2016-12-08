package com.bitunified.ledconfig.configuration.parser.steps.types;


import com.bitunified.ledconfig.configuration.parser.steps.ModelResult;
import com.bitunified.ledconfig.configuration.parser.steps.ParseStep;
import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.parts.Part;

import java.util.List;

public class ParserStepRealModelComposed extends ParseStepBase implements ParseStep{


    private final Integer dataBegin;
    private final Integer dataEnd;


    public ParserStepRealModelComposed(Integer step, Class<? extends Model> model, String regex, String errorMessage, Integer dataBegin, Integer dataEnd) {
    super(step,false,model,regex,errorMessage);

        this.dataBegin = dataBegin;
        this.dataEnd = dataEnd;
    }

    public ModelResult create(String productcode, List<Part> parts) {

        for (Part part : parts) {
            if (checkModel(part)) {
                RealModel product = createPart(part);


                        if (dataEnd != null && dataBegin<productcode.length() && dataEnd<=productcode.length()) {
                            String length = productcode.substring(dataBegin, dataEnd);
                            Integer lengthNumber=parseInteger(length);
                            if (lengthNumber!=null) {
                                product.setDimension(new Dimension(new Integer(length)));

                            }


                        }

                        return new ModelResult(product);
                    }





        }
        return new ModelResult(getErrorMessage());
    }


}
