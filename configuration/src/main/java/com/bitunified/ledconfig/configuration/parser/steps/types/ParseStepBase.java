package com.bitunified.ledconfig.configuration.parser.steps.types;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.parts.Part;

public abstract class ParseStepBase {



    private final String regex;
    private final Class<? extends Model> modelClass;
    private String errorMessage;
    protected boolean checkModel(Part part) {
        if (part.getProduct()!=null && isInstance(part.getProduct())){
            return true;
        }
        if (part.getConfigModel()!=null && isInstance(part.getConfigModel())){
            return true;
        }
        return false;
    }

    private boolean isInstance(Model model) {
        if (model.getClass().getName().equals(modelClass.getName()) || modelClass.isAssignableFrom(model.getClass())){
            return true;
        }
        return false;
    }

    public ParseStepBase( Class<? extends Model> model, String regex, String errorMessage) {

        this.modelClass = model;
        this.regex = regex;
        this.errorMessage = errorMessage;
    }
    protected Integer parseInteger(String length) {
        try{
            return Integer.parseInt(length);
        }catch(Exception e){
            return null;
        }
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
