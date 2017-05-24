package com.bitunified.server.models;


import com.bitunified.ledconfig.domain.Model;

import java.util.ArrayList;
import java.util.List;

public class Models {

    private List<Model> models = new ArrayList<>();

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }
}
