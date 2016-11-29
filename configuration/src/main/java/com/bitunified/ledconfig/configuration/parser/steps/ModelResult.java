package com.bitunified.ledconfig.configuration.parser.steps;


import com.bitunified.ledconfig.domain.Model;

public class ModelResult {

    private final String errorMessage;
    private Model model;
    public ModelResult(String errorMessageLength) {
        this.errorMessage = errorMessageLength;
    }
    public ModelResult(Model model) {
        this.model = model;
        errorMessage = null;
    }
    public ModelResult(Model model, String errorMessageLength) {
        this.model = model;
        errorMessage = errorMessageLength;
    }


    public Model getModel() {
        return model;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
