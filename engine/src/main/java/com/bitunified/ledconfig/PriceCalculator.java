package com.bitunified.ledconfig;


import com.bitunified.ledconfig.composedproduct.ComposedProduct;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.message.Message;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.domain.product.ModelResult;
import com.bitunified.ledconfig.parts.Part;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceCalculator {


    private static final BigDecimal FACTOR = BigDecimal.valueOf(1.39 );

    public BigDecimal calculate(ConfigResult configResult){
        ComposedProduct composedProduct=null;

        for (Object model : configResult.getModels()) {

            if (model instanceof ComposedProduct){
                System.out.println("ComposedProduct: "+model);
                composedProduct= (ComposedProduct) model;
                break;
            }

        }
        BigDecimal totalPrice=new BigDecimal(0);
        for (Part part:composedProduct.getProducts()){
            System.out.println(part);
            if (part.getPrice()!=null) {
                totalPrice = totalPrice.add(part.getPrice());
            }
        }
        for (ModelResult modelResult:composedProduct.getModelResults()){
            if (modelResult!=null) {
                Part part = modelResult.getPart();
                if (part != null && part.getPrice() != null) {
                    totalPrice = totalPrice.add(part.getPrice());
                }
            }
        }
        BigDecimal total=FACTOR.multiply(totalPrice);
        total=total.setScale(2, RoundingMode.HALF_UP);
        System.out.println("TotalPrice: "+total);
        return total;
    }
}
