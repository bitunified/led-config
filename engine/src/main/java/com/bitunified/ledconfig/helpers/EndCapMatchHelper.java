package com.bitunified.ledconfig.helpers;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.Orientation;
import com.bitunified.ledconfig.domain.product.cable.cableconfig.CableEntry;
import com.bitunified.ledconfig.domain.product.mounting.EndCap;
import com.bitunified.ledconfig.domain.product.mounting.Mounting;
import com.bitunified.ledconfig.domain.product.profile.Profile;
import com.bitunified.ledconfig.parts.Part;
import com.bitunified.ledconfig.parts.Relatable;

import java.util.*;

public class EndCapMatchHelper {

    private static void findEndCapsFromModel(Set<Part> endCaps, Model model, Orientation orientation){
        System.out.println("Match model:"+model);
        if (model.getRelation()!=null ) {
            Set<Relatable> relations=null;
            if (orientation==Orientation.Left){
                relations=model.getRelation().getRelateToLeft();
            }else{
                relations=model.getRelation().getRelateToRight();
            }
            for (Relatable relation : relations) {
                if (relation instanceof Part) {
                    System.out.println("Product: "+((Part) relation).getProduct());
                    if (((Part) relation).getProduct() != null && (((Part) relation).getProduct() instanceof EndCap)) {


                        endCaps.add( (Part) relation);

                    }
                }


            }
        }
    }
    private static EndCapMostWanted findMatches(Set<Part> endCaps,Orientation orientation){
        Map<Part, Integer> map = new HashMap<Part, Integer>();

        for (Part temp : endCaps) {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }
        Integer ci = 0;
        for (Integer i : map.values()) {
            if (i > ci) {
                ci = i;
            }
        }

        EndCapMostWanted endCapMostWanted
                =new EndCapMostWanted();
        for (Map.Entry<Part, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(ci)) {
                endCapMostWanted.endCap=entry.getKey();
                endCapMostWanted.count=ci;
                System.out.println("H"+ci+" end cap for "+orientation+": " + entry.getKey());
            }
            System.out.println("Key : " + entry.getKey() + " Value : "
                    + entry.getValue());
        }
        return endCapMostWanted;
    }
    public static class EndCapMostWanted{
        int count;
        Part endCap;

        public int getCount() {
            return count;
        }

        public Part getEndCap() {
            return endCap;
        }
    }
    public static Map<Orientation,EndCapMostWanted> match(Profile profile, Mounting mounting){
        Set<Part> endCapsLeft = new HashSet<>();
        Set<Part> endCapsRight = new HashSet<>();

        findEndCapsFromModel(endCapsLeft,profile,Orientation.Left);
        findEndCapsFromModel(endCapsRight,profile,Orientation.Right);

        findEndCapsFromModel(endCapsLeft,mounting,Orientation.Left);
        findEndCapsFromModel(endCapsRight,mounting,Orientation.Right);


        EndCapMostWanted mostWantedLeft=findMatches(endCapsLeft,Orientation.Left);
        EndCapMostWanted mostWantedRight=findMatches(endCapsRight,Orientation.Right);

        Map<Orientation,EndCapMostWanted> result=new HashMap<>();
        result.put(Orientation.Left,mostWantedLeft);
        result.put(Orientation.Right,mostWantedRight);
        return result;
    }
}
