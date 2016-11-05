package com.bitunified.ledconfig.domain;


public class Property {

    private final String name;
    private Object value;

    public Property(String name, String value){
        this.name=name;
        this.value=value;
    }

    public Object getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setValue(Object value) {
        this.value=value;

    }
}
