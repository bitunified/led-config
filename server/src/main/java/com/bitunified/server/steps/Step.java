package com.bitunified.server.steps;

import com.bitunified.ledconfig.domain.Model;

import java.util.List;

public class Step {
    private String description;
    private List<Model> models;

    public Step(){

    }

    public Step(String description, List<Model> models) {
        this.description = description;
        this.models = models;
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
}
