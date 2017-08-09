/*
 * Copyright 2010 BitUnified B.V. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bitunified.ledconfig;

import com.bitunified.ledconfig.composedproduct.ComposedProduct;
import com.bitunified.ledconfig.configuration.parser.steps.Parser;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.instruction.InstructionMessage;
import com.bitunified.ledconfig.domain.message.Message;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;
import com.bitunified.ledconfig.parts.NotExistingPart;
import com.bitunified.ledconfig.parts.Part;
import com.bitunified.ledconfig.productconfiguration.ModelChosenStep;
import com.bitunified.ledconfig.productconfiguration.ProductConfiguration;
import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderConfiguration;
import org.kie.internal.builder.KnowledgeBuilderFactory;

import java.util.*;
import java.util.stream.Collectors;


/**
 * This is a sample file to launch a rule package from a rule source file.
 */
public class LedConfig {

    public final void main(final String[] args) {
        rules(null, null);
    }

    public final ProductConfigResult rules(final ProductConfiguration productConfigration, Parser parser) {
        // KieServices is the factory for all KIE services 
        KieServices ks = KieServices.Factory.get();

        // From the kie services, a container is created from the classpath
        KieContainer kc = ks.getKieClasspathContainer();

        KnowledgeBuilderConfiguration kbConfig =
                KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration();

        kbConfig.setProperty("drools.dialect.mvel.strict", "false");

        KnowledgeBuilder kBuilder =
                KnowledgeBuilderFactory.newKnowledgeBuilder(kbConfig);

        return execute(kc, productConfigration, parser);

    }

    public ProductConfigResult execute(KieContainer kc, ProductConfiguration productConfiguration, Parser parser) {

        KieSession ksession = kc.newKieSession("LedConfigKS");
        try {
            // From the container, a session is created based on
            // its definition and configuration in the META-INF/kmodule.xml file

            // Once the session is created, the application can interact with it
            // In this case it is setting a global as defined in the
            // org/drools/examples/helloworld/HelloWorld.drl file
            Set<Message> messages = new HashSet<>();
            ksession.setGlobal("messages", messages);

            List<InstructionMessage> instructions = new ArrayList<>();
            ksession.setGlobal("instructions", instructions);

            Map<Part, Double> partCountList = new HashMap<>();
            ksession.setGlobal("partCountList", partCountList);

            Map<Integer, List<Message>> messageMap = new HashMap<>();


            ksession.setGlobal("messageMap", messageMap);


            Map<String, Part> parts = new HashMap<>();
            for (Part part : parser.getParts()) {
                parts.put(part.getId(), part);
            }
            ksession.setGlobal("parts", parts);
            // The application can also setup listeners
            ksession.addEventListener(new DebugAgendaEventListener());
            ksession.addEventListener(new DebugRuleRuntimeEventListener());

            // To setup a file based audit logger, uncomment the next line
            // KieRuntimeLogger logger = ks.getLoggers().newFileLogger( ksession, "./helloworld" );

            // To setup a ThreadedFileLogger, so that the audit view reflects events whilst debugging,
            // uncomment the next line
            // KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger( ksession, "./helloworld", 1000 );

            // The application can insert facts into the session


            for (ModelChosenStep m : productConfiguration.getModelsForSteps()) {

                Model modelToAdd = parser.getModels().stream().filter(ml -> ml.equals(m.getChosenModel())).collect(Collectors.toList()).stream().findFirst().orElse(null);
                if (ComposedProduct.class.equals(m.getChosenModel().getClass())){
                    modelToAdd=m.getChosenModel();
                }
                if (LedStrip.class.isAssignableFrom(m.getChosenModel().getClass())){
                    modelToAdd=m.getChosenModel();
                }
                if (modelToAdd!=null) {
                    ksession.insert(modelToAdd);
                }
            }



            ksession.fireAllRules();


            Collection<Model> sortedModels = (Collection<Model>) ksession.getObjects();

            for (Object model : sortedModels) {
                if (model instanceof Model && !(model instanceof ComposedProduct)) {
                    Model m = (Model) model;
                    if (m.getStep() != null && m.getName() != null && messageMap.get(m.getStep()) != null) {
                        messageMap.get(m.getStep()).add(new Message(m));
                        for (Part p : parts.values()) {

                            if (!(p instanceof NotExistingPart)) {
                                if (p.getProduct() != null && p.getProduct().equals(m)) {
                                    //System.out.println("Add to partList: "+p);
                                    //partList.add(p);
                                }
                                if (p.getConfigModel() != null && p.getConfigModel().equals(m)) {
                                    //partList.add(p);
                                }
                            }
                        }
                    }
                    System.out.println("Model: " + model);
                }

                if (model instanceof Part) {
                    System.out.println("Part:  " + ((Part) model).getProduct());
                }

            }

            for (Map.Entry<Part, Double> pl : partCountList.entrySet()) {
                if (pl != null) {
                    System.out.println("Part from partlist: " + pl.getValue() + " aantal: " + pl.getKey());
                }
            }
            for (Message msg : messages) {
                if (msg.getStep() != null && messageMap.get(msg.getStep()) != null) {
                    messageMap.get(msg.getStep()).add(msg);

                }
            }


            // Remove comment if using logging
            // logger.close();

            // and then dispose the session
            ksession.dispose();
            ksession.destroy();

            return new ProductConfigResult(new ArrayList<>(), messages, messageMap, ksession.getObjects(), partCountList, instructions);
        } catch (Exception e) {
            ksession.dispose();
            ksession.destroy();

        }
        finally {
            ksession.dispose();
            ksession.destroy();
        }
        return null;
    }

    public void dispose() {

    }
}
