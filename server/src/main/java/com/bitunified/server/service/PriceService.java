package com.bitunified.server.service;


import com.bitunified.ledconfig.LedConfig;
import com.bitunified.ledconfig.ProductConfigResult;
import com.bitunified.ledconfig.configuration.parser.steps.Parser;
import com.bitunified.ledconfig.parts.Part;
import com.bitunified.ledconfig.productconfiguration.ModelChosenStep;
import com.bitunified.ledconfig.productconfiguration.ProductConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PriceService {

    private Parser parser;

    public PriceService(Parser parser) {
        this.parser = parser;
    }

    public Part getPart(ProductConfiguration productConfiguration, Integer currentStep) {

        LedConfig ledConfig = new LedConfig();
        ProductConfigResult productConfigResult = ledConfig.rules(productConfiguration, parser);

        Optional<ModelChosenStep> lastModelChosenStep = productConfiguration.getModelsForSteps().stream().filter(item -> item.getStep().getStepindex().equals(currentStep)).findFirst();
        Stream<Map.Entry<Part, Double>> matchedModels;
        List<Part> partsMatched = new ArrayList<>();
        if (lastModelChosenStep.isPresent() && productConfigResult!=null) {
            matchedModels = productConfigResult.getPartList().entrySet().stream().filter(pr -> pr.getKey().getProduct() != null).filter(modelExists -> modelExists.getKey().getProduct().getId() != null).filter(f -> f.getKey().getProduct().equals(lastModelChosenStep.get().getChosenModel()));
            matchedModels.forEach(en -> partsMatched.add(en.getKey()));

        } else {
            return null;
        }
        if (partsMatched.size() > 0) {
            return partsMatched.stream().filter(p -> p.getProduct() != null).filter(p -> p.getProduct().equals(lastModelChosenStep.get().getChosenModel())).collect(Collectors.toList()).get(0);
        }


        return null;

    }
}
