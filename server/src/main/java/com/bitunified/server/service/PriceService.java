package com.bitunified.server.service;


import com.bitunified.ledconfig.configuration.parser.steps.Parser;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.parts.Part;

import java.util.List;
import java.util.stream.Collectors;

public class PriceService {

    private Parser parser;
    public PriceService(Parser parser) {
        this.parser=parser;
    }

    public Part getPart(Model model) {
        List<Part> parts = parser.getParts().stream().filter(f -> f.getProduct() != null).filter(part -> part.getProduct().equals(model)).collect(Collectors.toList());
        if (parts.size()>0){

        return parts.get(0);
        }
        parts = parser.getParts().stream().filter(f -> f.getConfigModel() != null).filter(part -> part.getConfigModel().equals(model)).collect(Collectors.toList());
        if (parts.size()>0){

            return parts.get(0);
        }
        return null;

    }
}
