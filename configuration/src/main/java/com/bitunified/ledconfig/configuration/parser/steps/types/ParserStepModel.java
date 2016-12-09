package com.bitunified.ledconfig.configuration.parser.steps.types;


import com.bitunified.ledconfig.configuration.parser.steps.ModelResult;
import com.bitunified.ledconfig.configuration.parser.steps.ParseStep;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.parts.Part;

import java.util.List;

public class ParserStepModel extends ParseStepBase implements ParseStep {


    private final Integer begin;
    private final Integer end;

    public ParserStepModel(Integer step, boolean mandatory, Integer begin, Integer end, Class<? extends Model> model, String regex, String errorMessage) {
        super(step,mandatory,model,regex,errorMessage);
        this.begin = begin;
        this.end = end;
    }

    public ModelResult create(String productcode, List<Part> parts) {
        String code=parse(productcode);
        for (Part part : parts) {
            if (checkModel(part)) {
                Model product = createPartModel(part);
                if (code != null) {
                    if (part.getCode().equalsIgnoreCase(code)) {

                        return new ModelResult(product);
                    }
                }
            }
        }
        return new ModelResult(getErrorMessage());
    }

    private String parse(String productcode) {
        if ((begin==null || end==null)||(begin.equals(end))){
            return null;
        }
        return
                productcode.substring(begin,end);
    }


}
