package com.bitunified.ledconfig.parts;


public class SimplePart {

    private Double price;
    private String name;

    public SimplePart(){

    }
    public SimplePart(Double price, String name) {
        this.price = price;
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
