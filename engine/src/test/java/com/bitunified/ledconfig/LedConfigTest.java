package com.bitunified.ledconfig;

import com.bitunified.ledconfig.configuration.parser.steps.Parser;
import com.bitunified.ledconfig.domain.product.profile.ProfileL20;
import com.bitunified.ledconfig.productconfiguration.ModelChosenStep;
import com.bitunified.ledconfig.productconfiguration.ProductConfiguration;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore
public class LedConfigTest {

    static Parser parser = null;

    public LedConfigTest() {

        if (parser == null) {
            parser = new Parser();
            parser.createParts();

        }
    }
    //CABDD1473000b

    @Test
    public void testConfig1() {
        ProductConfiguration productConfiguration = new ProductConfiguration();
        List<ModelChosenStep> steps = new ArrayList<>();
        ModelChosenStep modelstep=new ModelChosenStep();
        ProfileL20 l20=new ProfileL20();

        modelstep.setChosenModel(l20);

        steps.add(modelstep);
        productConfiguration.setModelsForSteps(steps);
        execute(productConfiguration);
    }

    private void execute(ProductConfiguration productConfiguration) {
        LedConfig ledConfig = new LedConfig();

        ProductConfigResult result = ledConfig.rules(productConfiguration,parser);
        System.out.println(result.getMessages());
        System.out.println(result.getModels());
    }


}