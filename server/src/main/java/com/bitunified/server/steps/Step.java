package com.bitunified.server.steps;

import com.bitunified.ledconfig.domain.Model;

import java.util.List;

public class Step {
    private String description;
    private Integer stepindex;
    private StepType type;
    private List<Model> models;

    public Step() {

    }

    public Step(Integer stepindex,String description, List<Model> models, StepType type) {
        this.description = description;
        this.models = models;
        this.type = type;
        this.stepindex=stepindex;
    }

    public Step(Integer stepindex,String description, List<Model> models) {
        this.description = description;
        this.models = models;
        this.type = StepType.VALUES;
        this.stepindex=stepindex;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StepType getType() {
        return type;
    }

    public void setType(StepType type) {
        this.type = type;
    }

    public Integer getStepindex() {
        return stepindex;
    }

    public void setStepindex(Integer stepindex) {
        this.stepindex = stepindex;
    }
}
