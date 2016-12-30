package com.bitunified.ledconfig.domain;


import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Margin implements Serializable {

    public Margin(int left,int right){
        this.left=left;
        this.right=right;
    }
    public Margin(){

    }

    private String leftStr;

    private Integer left;

    private Integer right;

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
        this.leftStr=left.toString();
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public String getLeftStr() {
        return leftStr;
    }

    public void setLeftStr(String leftStr) {
        this.leftStr = leftStr;
        this.left=Integer.parseInt(leftStr);
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
