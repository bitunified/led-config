package com.bitunified.ledconfig.domain;


import com.bitunified.ledconfig.parts.Relatable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Relation {

    private List<Relatable> relateTo=new ArrayList<Relatable>();

    public Map<Orientation, List<Relatable>> getRelatableMap() {
        return relatableMap;
    }

    public void setRelatableMap(Map<Orientation, List<Relatable>> relatableMap) {
        this.relatableMap = relatableMap;
    }

    private Map<Orientation,List<Relatable>> relatableMap=new HashMap<Orientation, List<Relatable>>();

    public Relation(){

    }

    public Relation(Relatable relateTo){

       //addRelation(relateTo,null);
        relateTo.setRelates(this);
    }

    private void addRelation(Relatable relateTo,Orientation orientation){
        this.relateTo.add(relateTo);
        if (orientation==null){
            List<Relatable> rels=relatableMap.get(Orientation.Left);
            if (rels==null){
                rels=new ArrayList<Relatable>();
            }
            rels.add(relateTo);
            relatableMap.put(Orientation.Left,rels);

            rels=relatableMap.get(Orientation.Right);
            if (rels==null){
                rels=new ArrayList<Relatable>();
            }
            rels.add(relateTo);
            relatableMap.put(Orientation.Right,rels);

        }else{
            List<Relatable> rels=relatableMap.get(orientation);
            if (rels==null){
                rels=new ArrayList<Relatable>();
            }
            rels.add(relateTo);
            relatableMap.put(orientation,rels);
        }

    }
    public List<Relatable> getRelateTo() {
        return relateTo;
    }


    public void addRelateTo(Relatable part,Orientation orientation) {

        this.addRelation(part,orientation);
    }


}
