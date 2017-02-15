package com.bitunified.ledconfig.helpers;


import com.bitunified.ledconfig.parts.Part;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class PartListHelper {

    public static void addToPartCountList(Map<Part,Double> partDoubleMap,Part part, Double add){
        if (part!=null) {
            Double count = partDoubleMap.get(part);
            if (count == null) {
                count = 0d;
            }

            partDoubleMap.put(part, round(count + add,2));
        }
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
