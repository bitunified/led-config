package com.bitunified.ledconfig.configuration.parser.steps.types;


import com.bitunified.ledconfig.configuration.parser.steps.ParseStep;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.domain.product.ModelResult;
import com.bitunified.ledconfig.parts.Part;

import java.util.List;
import java.util.Set;

public class ParserStepRealModel extends ParseStepBase implements ParseStep {


    private final Integer begin;
    private final Integer end;

    public ParserStepRealModel(Integer step,boolean mandatory,Integer begin, Integer end, Class<? extends Model> model, String regex, String errorMessage) {
        super(step,mandatory,model,regex,errorMessage);
        this.begin = begin;
        this.end = end;
    }

    public ModelResult create(String productcode, Set<Part> parts) {
        String code=null;
        try {
             code = parse(productcode);
        }catch(Exception e) {
            return new ModelResult(getErrorMessage());
        }
        for (Part part : parts) {
            if (checkModel(part)) {
                RealModel product = createPart(part);
                if (code != null) {
                    if (product.getCode().equalsIgnoreCase(code)) {

                        return new ModelResult(product,part);
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
        if (begin>productcode.length() || end>productcode.length()){
            return parseReverse(productcode);
        }
        return       productcode.substring(begin,end);
    }
    private String parseReverse(String productcode) {

        int diff=end-begin;
        return
                productcode.substring(productcode.length()-diff,productcode.length());
    }

}
