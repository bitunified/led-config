package com.bitunified.ledconfig.domain;


import java.util.HashSet;
import java.util.Set;

public class Model {
    private String name;


    private Set<Property> properies=new HashSet<Property>();



    public void addProperty(Property property){
        this.properies.add(property);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Property getProperty(String propName) {
        for (Property prop:properies){
            if (prop.getName().equalsIgnoreCase(propName)){
                return prop;
            }
        }
        return null;
    }

    public Set<Property> getProperies() {
        return properies;
    }
}
