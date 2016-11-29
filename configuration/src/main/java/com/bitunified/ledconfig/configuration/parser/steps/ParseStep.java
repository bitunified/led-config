package com.bitunified.ledconfig.configuration.parser.steps;


import com.bitunified.ledconfig.configuration.parser.steps.types.ParseStepOptional;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.parts.Part;

import java.util.List;

public interface ParseStep extends ParseStepOptional{

    ModelResult create(String productcode, List<Part> parts);

}
