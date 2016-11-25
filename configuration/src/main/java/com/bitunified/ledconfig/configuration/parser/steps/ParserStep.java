package com.bitunified.ledconfig.configuration.parser.steps;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.parts.Part;

import java.util.List;

public class ParserStep {


    private final String regex;
    private final Class<? extends Model> modelClass;
    private final Integer begin;
    private final Integer end;
    private final Integer dataEnd;
    private String errorMessage;

    public ParserStep(Integer begin, Integer end, Class<? extends Model> model, String regex, String errorMessage, Integer dataEnd) {
        this.begin = begin;
        this.end = end;
        this.modelClass = model;
        this.regex = regex;
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
                            String length = productcode.substring(end, dataEnd);
                            product.setDimension(new Dimension(new Integer(length)));

                        }
                        return product;
                    }
                } else {

                    if (dataEnd != null) {
                        String length = productcode.substring(end, dataEnd);
                        product.setDimension(new Dimension(new Integer(length)));

                    }
                    return product;
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
}
