package com.bitunified.ledconfig.configuration.parser.steps.types;


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
    private String errorMessage;

    public ParserStepRealModelWithLength(Integer begin, Integer end, Class<? extends Model> model, String regex, String errorMessage, Integer dataBegin, Integer dataEnd) {
        super(model,regex,errorMessage);
        this.begin = begin;
        this.end = end;

        this.dataBegin = dataBegin;
        this.dataEnd = dataEnd;
    }

    public Model create(String productcode, List<Part> parts) {
        String code=parse(productcode);
        for (Part part : parts) {
            if (part.getProduct().getClass().getName().equals(modelClass.getName())) {
                RealModel product = part.getProduct();
                if (code != null) {
                    if (part.getCode().equalsIgnoreCase(code)) {

                        if (dataEnd != null) {
                            String length = productcode.substring(dataBegin, dataEnd);
                            product.setDimension(new Dimension(new Integer(length)));

                        }

                        return product;
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

    public String getRegex() {
        return regex;
    }

    public Class<? extends Model> getModel() {
        return modelClass;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getDataBegin() {
        return dataBegin;
    }

    @Override
    public boolean isOptional() {
        return false;
    }
}
