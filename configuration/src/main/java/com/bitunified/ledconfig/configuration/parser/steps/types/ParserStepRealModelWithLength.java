package com.bitunified.ledconfig.configuration.parser.steps.types;


import com.bitunified.ledconfig.configuration.parser.steps.ModelResult;
import com.bitunified.ledconfig.configuration.parser.steps.ParseStep;
import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.parts.Part;

import java.util.List;

public class ParserStepRealModelWithLength extends ParseStepBase implements ParseStep{



    private final Integer begin;
    private final Integer end;

    private final Integer dataBegin;
    private final Integer dataEnd;

    private final String errorMessageLength;
    public ParserStepRealModelWithLength(Integer begin, Integer end, Class<? extends Model> model, String regex, String errorMessage, Integer dataBegin, Integer dataEnd,String errorMessageLength) {
        super(true,model,regex,errorMessage);
        this.errorMessageLength=errorMessageLength;
        this.begin = begin;
        this.end = end;

        this.dataBegin = dataBegin;
        this.dataEnd = dataEnd;
    }



    public ModelResult create(String productcode, List<Part> parts) {

        String code=parse(productcode);
        for (Part part : parts) {
            if (checkModel(part)) {
                RealModel product = part.getProduct();
                if (code != null) {
                    if (part.getCode().equalsIgnoreCase(code)) {

                        if (dataEnd != null) {
                            String length = productcode.substring(dataBegin, dataEnd);
                            Integer lengthInt=parseInteger(length);
                            if (lengthInt!=null) {
                                product.setDimension(new Dimension(lengthInt));
                            }else{
                                return new ModelResult(errorMessageLength);
                            }
                        }

                        return new ModelResult(product);
                    }



                }
            }
        }
        return null;
    }




    private String parse(String productcode) {
        if ((begin==null || end==null)||(begin.equals(end))){
            return null;
        }
        return
                productcode.substring(begin,end);
    }

    public Integer getDataBegin() {
        return dataBegin;
    }


    public String getErrorMessageLength() {
        return errorMessageLength;
    }
}
