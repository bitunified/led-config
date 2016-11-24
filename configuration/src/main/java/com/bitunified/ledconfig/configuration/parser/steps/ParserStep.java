package com.bitunified.ledconfig.configuration.parser.steps;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.parts.Part;

import java.util.List;

public class ParserStep {


    private final String regex;
    private final Class<? extends Model> model;
    private final Integer end;
    private final Integer begin;

    public ParserStep(Integer begin, Integer end, Class<? extends Model> model, String regex) {
        this.begin = begin;
        this.end = end;
        this.model = model;
        this.regex = regex;
    }

    public Model create(String productcode, List<Part> parts) {
        String code=parse(productcode);
        for (Part part : parts) {
            if (part.getProduct().getClass().equals(model) && part.getCode().equalsIgnoreCase(code)){
                return part.getProduct();
            }
        }
        return null;
    }

    private String parse(String productcode) {
        return productcode.substring(begin,end);
    }

    public String getRegex() {
        return regex;
    }

    public Class<? extends Model> getModel() {
        return model;
    }
}
