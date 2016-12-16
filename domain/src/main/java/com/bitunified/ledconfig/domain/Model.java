package com.bitunified.ledconfig.domain;


import com.bitunified.ledconfig.domain.description.Descriptive;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Model extends Descriptive implements StepConfig {
    private String name="";

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
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
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
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (step != null ? step.hashCode() : 0);
        result = 31 * result + (properies != null ? properies.hashCode() : 0);
        return result;
    }
}
