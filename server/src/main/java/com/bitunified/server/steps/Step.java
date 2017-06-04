package com.bitunified.server.steps;

import com.bitunified.ledconfig.domain.Model;

import java.util.List;

public class Step {
    private String description;
    private StepType type;
    private List<Model> models;

    public Step() {

    }

    public Step(String description, List<Model> models, StepType type) {
        this.description = description;
        this.models = models;
        this.type = type;
    }

    public Step(String description, List<Model> models) {
        this.description = description;
        this.models = models;
        this.type = StepType.VALUES;
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
}
