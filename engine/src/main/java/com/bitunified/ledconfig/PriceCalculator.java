package com.bitunified.ledconfig;


import com.bitunified.ledconfig.parts.Part;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class PriceCalculator {


    private static final BigDecimal FACTOR = BigDecimal.valueOf(1);

    public BigDecimal calculate(ProductConfigResult productConfigResult) {


        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Part, Double> pc : productConfigResult.getPartList().entrySet()) {

            if (pc.getValue() != null) {
                totalPrice = totalPrice.add(BigDecimal.valueOf(pc.getValue()));
            }
        }

        BigDecimal total = FACTOR.multiply(totalPrice);
        total = total.setScale(2, RoundingMode.HALF_UP);
        System.out.println("TotalPrice: " + total);
        return total;
    }
}
