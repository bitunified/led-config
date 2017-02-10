package com.bitunified.ledconfig.helpers;


import com.bitunified.ledconfig.parts.Part;

import java.util.Map;

public class PartListHelper {

    public static void addToPartCountList(Map<Part,Double> partDoubleMap,Part part, Double add){
        if (part!=null) {
            Double count = partDoubleMap.get(part);
            if (count == null) {
                count = 0d;
            }
            partDoubleMap.put(part, count + add);
        }
    }
}
