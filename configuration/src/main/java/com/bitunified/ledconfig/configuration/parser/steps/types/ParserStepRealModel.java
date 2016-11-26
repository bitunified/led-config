package com.bitunified.ledconfig.configuration.parser.steps.types;


import com.bitunified.ledconfig.configuration.parser.steps.ParseStep;
import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.parts.Part;

import java.util.List;

public class ParserStepRealModel extends ParseStepBase implements ParseStep {


    private final Integer begin;
    private final Integer end;
    private String errorMessage;

    public ParserStepRealModel(Integer begin, Integer end, Class<? extends Model> model, String regex, String errorMessage) {
        super(model,regex,errorMessage);
        this.begin = begin;
        this.end = end;
    }

    public Model create(String productcode, List<Part> parts) {
        String code=parse(productcode);
        for (Part part : parts) {
            if (part.getProduct().getClass().getName().equals(modelClass.getName())) {
                RealModel product = part.getProduct();
                if (code != null) {
                    if (part.getCode().equalsIgnoreCase(code)) {

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

    @Override
    public boolean isOptional() {
        return false;
    }
}
