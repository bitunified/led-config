package com.bitunified.server.service;

import com.bitunified.ledconfig.configuration.parser.steps.Parser;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.productconfiguration.steps.Step;
import com.bitunified.ledconfig.productconfiguration.steps.StepType;
import com.bitunified.ledconfig.productconfiguration.steps.Steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class StepService {

    public Steps getSteps(Parser parser) {

        Steps steps = new Steps();

        Map<Integer, List<Model>> groupedModels = parser.getModels().stream().filter(f -> f.getStep() != null).collect(Collectors.groupingBy(Model::getStep));

        List<Step> stepListValues = groupedModels.entrySet().stream().filter(f -> f.getKey() != 4).map(t -> new Step(t.getKey(), t.getKey() == null ? "" : "Step " + t.getKey().toString(), t.getValue())).collect(Collectors.toList());
        List<Step> stepListNumbers = new ArrayList<>();
        stepListNumbers.add(new Step(4, "Step 4", new ArrayList<>(), StepType.NUMBER));

        List<Step> allSteps = new ArrayList<>();
        allSteps.addAll(stepListValues);
        allSteps.addAll(stepListNumbers);
        steps.setSteps(allSteps);
        return steps;
    }

    private static RealModel getStep(RealModel t) {
        return null;
    }


}
