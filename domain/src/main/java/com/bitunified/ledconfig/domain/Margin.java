package com.bitunified.ledconfig.domain;


import java.io.Serializable;

public class Margin implements Serializable {

    private Integer left;

    private Integer right;

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }
}
