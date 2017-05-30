package com.bitunified.server.service;

import com.bitunified.ledconfig.configuration.parser.steps.Parser;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.server.models.Models;
import com.bitunified.server.steps.Step;
import com.bitunified.server.steps.Steps;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class StepService {

    public Steps getSteps() {
        Models models = new Models();

        Parser parser = new Parser();
        parser.createParts();

        models.setModels(parser.getModels());
        Steps steps = new Steps();

        Map<Integer, List<Model>> groupedModels = parser.getModels().stream().collect(Collectors.groupingBy(Model::getStep));
        List<Step> stepList = groupedModels.entrySet().stream().map(t -> new Step(t.getKey()==null?"":t.getKey().toString(), t.getValue())).collect(Collectors.toList());
        steps.setSteps(stepList);
        return steps;
    }


}
