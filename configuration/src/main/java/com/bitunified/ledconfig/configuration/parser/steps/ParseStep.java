package com.bitunified.ledconfig.configuration.parser.steps;


import com.bitunified.ledconfig.parts.Part;

import java.util.List;

public interface ParseStep {

    ModelResult create(String productcode, List<Part> parts);

    boolean isMantatory();
}
