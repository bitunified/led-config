package com.bitunified.server.models;


import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.server.steps.Step;

public class ModelChosenStep {


    private Step step;
    private RealModel chosenModel;
    private int modelValue;

    public int getModelValue() {
        return modelValue;
    }

    public void setModelValue(int modelValue) {
        this.modelValue = modelValue;
    }

    public RealModel getChosenModel() {
        return chosenModel;
    }

    public void setChosenModel(RealModel chosenModel) {
        this.chosenModel = chosenModel;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }
}
