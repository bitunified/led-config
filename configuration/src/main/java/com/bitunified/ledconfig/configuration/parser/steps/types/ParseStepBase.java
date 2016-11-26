package com.bitunified.ledconfig.configuration.parser.steps.types;


import com.bitunified.ledconfig.domain.Model;

public abstract class ParseStepBase {



    protected final String regex;
    protected final Class<? extends Model> modelClass;

    public ParseStepBase( Class<? extends Model> model, String regex, String errorMessage) {

        this.modelClass = model;
        this.regex = regex;
        this.errorMessage = errorMessage;
    }
    private String errorMessage;

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
