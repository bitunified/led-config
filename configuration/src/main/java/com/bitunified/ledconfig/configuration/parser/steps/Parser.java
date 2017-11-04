package com.bitunified.ledconfig.configuration.parser.steps;


import com.bitunified.ledconfig.composedproduct.ComposedProduct;
import com.bitunified.ledconfig.configuration.parser.steps.inferface.ParserInterface;
import com.bitunified.ledconfig.domain.*;
import com.bitunified.ledconfig.domain.I18N.Locale;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;
import com.bitunified.ledconfig.domain.product.PCB.types.*;
import com.bitunified.ledconfig.domain.product.accessoires.CableChannel;
import com.bitunified.ledconfig.domain.product.accessoires.Clip;
import com.bitunified.ledconfig.domain.product.cable.Cable;
import com.bitunified.ledconfig.domain.product.cable.cableconfig.*;
import com.bitunified.ledconfig.domain.product.cover.types.*;
import com.bitunified.ledconfig.domain.product.mounting.*;
import com.bitunified.ledconfig.domain.product.profile.Profile;
import com.bitunified.ledconfig.domain.product.profile.ProfileL20;
import com.bitunified.ledconfig.domain.relation.RelationDefinition;
import com.bitunified.ledconfig.domain.relation.RelationState;
import com.bitunified.ledconfig.parts.NotExistingPart;
import com.bitunified.ledconfig.parts.Part;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parser  {
    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    private Set<Part> parts = new HashSet<Part>();

    private Set<Relation> relations = new HashSet<Relation>();

    private List<RelationDefinition> relationDefinitions = new ArrayList<RelationDefinition>();

    private List<Model> models = new ArrayList<Model>();

    private ComposedProduct composedProduct;

    public void createParts() {
       ParserData parserData = new ParserData();
        parserData.createParts(models,relationDefinitions,parts);


        composedProduct = new ComposedProduct(null, null);
        composedProduct.setName("Product lengte");

        models.add(composedProduct);

    }






    public Set<Relation> getRelations() {
        return relations;
    }

    public List<Model> getModels() {
        return this.models;
    }

    public List<RelationDefinition> getRelationDefinitions() {
        return relationDefinitions;
    }
}
