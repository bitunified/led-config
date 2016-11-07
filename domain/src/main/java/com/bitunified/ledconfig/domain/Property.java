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

    public Property setValue(Object value) {
        this.value=value;
        return this;
    }

    @Override
    public String toString() {
        return "key:"+name+" value:"+value;
    }
}
