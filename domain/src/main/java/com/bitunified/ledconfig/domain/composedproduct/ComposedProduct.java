package com.bitunified.ledconfig.domain.composedproduct;

import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.product.Product;
import com.bitunified.ledconfig.domain.product.profile.Profile;

import java.util.HashSet;
import java.util.Set;


public class ComposedProduct extends Dimension {

    private Set<Model> products=new HashSet<Model>();

    public ComposedProduct(Integer totalWidth, Integer totalHeight) {
        super(totalWidth, totalHeight);
    }

    public void addProducts(Model model) {
        products.add(model);
    }
}
