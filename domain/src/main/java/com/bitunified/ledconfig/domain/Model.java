package com.bitunified.ledconfig.domain;


import com.bitunified.ledconfig.domain.description.Descriptive;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Model extends Descriptive implements StepConfig {
    private String name;

    private Integer step;

    private Map<String,Property> properies=new HashMap<String,Property>();



    public Property addProperty(Property property){
        this.properies.put(property.getName(),property);
        return property;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Property getProperty(String propName) {
        return properies.get(propName);

    }

    public Set getProperies() {
        return properies.entrySet();
    }

    @Override
    public String toString() {
        return "[name:"+this.name+" props:"+properies.toString()+"]";
    }

    @Override
    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        if (!name.equals(model.name)) return false;
        return step.equals(model.step);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + step.hashCode();
        return result;
    }
}
