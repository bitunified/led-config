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
import com.bitunified.ledconfig.configuration.parser.steps.ParseStep;
import com.bitunified.ledconfig.configuration.parser.steps.ParsedResult;
import com.bitunified.ledconfig.configuration.parser.steps.Parser;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.message.Message;
import com.bitunified.ledconfig.parts.Part;
import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderConfiguration;
import org.kie.internal.builder.KnowledgeBuilderFactory;

import java.util.*;


/**
 * This is a sample file to launch a rule package from a rule source file.
 */
public class LedConfig {

    public final void main(final String[] args) {
        rules(args);
    }

    public final ConfigResult rules(final String[] args) {
        // KieServices is the factory for all KIE services 
        KieServices ks = KieServices.Factory.get();

        // From the kie services, a container is created from the classpath
        KieContainer kc = ks.getKieClasspathContainer();

        KnowledgeBuilderConfiguration kbConfig =
                KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration();

        kbConfig.setProperty("drools.dialect.mvel.strict", "false");

        KnowledgeBuilder kBuilder =
                KnowledgeBuilderFactory.newKnowledgeBuilder(kbConfig);
        Parser parser=new Parser();
        return execute(kc, parser.parse(args[0]));

    }

    public ConfigResult execute(KieContainer kc, ParsedResult parsedResult) {


        // From the container, a session is created based on
        // its definition and configuration in the META-INF/kmodule.xml file
        KieSession ksession = kc.newKieSession("LedConfigKS");

        // Once the session is created, the application can interact with it
        // In this case it is setting a global as defined in the
        // org/drools/examples/helloworld/HelloWorld.drl file
        List<Message> messages = new ArrayList<Message>();
        ksession.setGlobal("messages",
                messages);
        Map<String,Part> parts = new HashMap<>();
        for (Part part:parsedResult.getParts()){
            parts.put(part.getId(),part);
        }
        ksession.setGlobal("parts",
                parts);
        // The application can also setup listeners
        ksession.addEventListener(new DebugAgendaEventListener());
        ksession.addEventListener(new DebugRuleRuntimeEventListener());

        // To setup a file based audit logger, uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newFileLogger( ksession, "./helloworld" );

        // To setup a ThreadedFileLogger, so that the audit view reflects events whilst debugging,
        // uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger( ksession, "./helloworld", 1000 );

        // The application can insert facts into the session

        for (Model model : parsedResult.getModels()) {
            ksession.insert(model);
        }

        ksession.fireAllRules();

        Map<Integer, List<Message>> messageMap = new HashMap<>();
        Set<Part> partList= new HashSet<>();

        for (ParseStep step : parsedResult.getSteps()) {
            List<Message> messagesInMap=messageMap.get(step.getStep());
            if (messagesInMap==null){
                messagesInMap=new ArrayList<>();
            }
            if (step.getModelResult() != null && step.getModelResult().getModel() != null) {

                messagesInMap.add(new Message(step.getModelResult().getModel().getName()));
                messageMap.put(step.getStep(), messagesInMap);
            } else {
                messagesInMap.add(new Message(step.getErrorMessage()));
                messageMap.put(step.getStep(), messagesInMap);
                messageMap.put(step.getStep(), messagesInMap);
            }

        }

        Collection<Model> sortedModels = (Collection<Model>) ksession.getObjects();

        for (Object model : sortedModels) {
            if (model instanceof Model && !(model instanceof ComposedProduct)) {
                Model m = (Model) model;
                if (m.getStep() != null && m.getName() != null) {
                    messageMap.get(m.getStep()).add(new Message(m));

                }
                System.out.println("Model: " + model);
            }

            if (model instanceof Part) {
                System.out.println("Part:  " + ((Part) model).getProduct());
            }
            if (model instanceof ComposedProduct){
                partList.addAll(((ComposedProduct) model).getProducts());
            }

        }

        for (Message msg : messages) {
            if (msg.getStep() != null) {
                messageMap.get(msg.getStep()).add(msg);

            }
        }


        // Remove comment if using logging
        // logger.close();

        // and then dispose the session
        ksession.dispose();
        ksession.destroy();

        return new ConfigResult(messages, messageMap, ksession.getObjects(),partList);

    }

    public void dispose() {

    }
}
