package com.bitunified.ledconfig.domain;


import java.io.Serializable;

public class Dimension implements Serializable{

    public Integer width;
    public Integer height;
    public Integer depth;

    public String getWidthStr() {
        return widthStr;
    }

    public void setWidthStr(String widthStr) {
        this.widthStr = widthStr;
    }

    public String widthStr;
    public Unit unit;

    public Dimension(Integer width, Integer height,Integer depth) {
        this.width=width;
        this.height=height;
        this.depth=depth;

    }

    public Dimension(Integer width, Integer height) {
        this.width=width;
        this.height=height;
    }
    public Dimension(Integer width) {
        this.width=width;

    }

    @Override
    public String toString() {
        return "[Width:" + (width!=null?width:"")+" Height:"+(height!=null?height:"")+"]";
    }
}
