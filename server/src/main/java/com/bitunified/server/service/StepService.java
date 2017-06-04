package com.bitunified.server.service;

import com.bitunified.ledconfig.configuration.parser.steps.Parser;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.server.models.Models;
import com.bitunified.server.steps.Step;
import com.bitunified.server.steps.StepType;
import com.bitunified.server.steps.Steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class StepService {

    public Steps getSteps() {
        Parser parser = new Parser();
        parser.createParts();

        Steps steps = new Steps();

        Map<Integer, List<Model>> groupedModels = parser.getModels().stream().filter(f->f.getStep()!=null).collect(Collectors.groupingBy(Model::getStep));

        List<Step> stepListValues = groupedModels.entrySet().stream().filter(f->f.getKey()!=2).map(t -> new Step(t.getKey(),t.getKey()==null?"":t.getKey().toString(), t.getValue())).collect(Collectors.toList());
        List<Step> stepListNumbers = groupedModels.entrySet().stream().filter(f->f.getKey()==2).map(t -> new Step(t.getKey(),t.getKey()==null?"":t.getKey().toString(), t.getValue(), StepType.NUMBER)).collect(Collectors.toList());

        List<Step> allSteps=new ArrayList<>();
        allSteps.addAll(stepListValues);
        allSteps.addAll(stepListNumbers);
        steps.setSteps(allSteps);
        return steps;
    }


}
