/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
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

import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.composedproduct.ComposedProduct;
import com.bitunified.ledconfig.domain.message.Message;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;
import com.bitunified.ledconfig.domain.product.PCB.types.RGBLedStrip;
import com.bitunified.ledconfig.domain.product.profile.Profile;
import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;


/**
 * This is a sample file to launch a rule package from a rule source file.
 */
public class LedConfig {

    public static final void main(final String[] args) {
        // KieServices is the factory for all KIE services 
        KieServices ks = KieServices.Factory.get();
        
        // From the kie services, a container is created from the classpath
        KieContainer kc = ks.getKieClasspathContainer();

        execute( kc );
    }

    public static void execute( KieContainer kc ) {
        // From the container, a session is created based on
        // its definition and configuration in the META-INF/kmodule.xml file
        KieSession ksession = kc.newKieSession("LedConfigKS");

        // Once the session is created, the application can interact with it
        // In this case it is setting a global as defined in the
        // org/drools/examples/helloworld/HelloWorld.drl file
        List<Message> messages=new ArrayList<Message>();
        ksession.setGlobal( "messages",
                            messages );

        // The application can also setup listeners
        ksession.addEventListener( new DebugAgendaEventListener() );
        ksession.addEventListener( new DebugRuleRuntimeEventListener() );

        // To setup a file based audit logger, uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newFileLogger( ksession, "./helloworld" );

        // To setup a ThreadedFileLogger, so that the audit view reflects events whilst debugging,
        // uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger( ksession, "./helloworld", 1000 );

        // The application can insert facts into the session

        final Profile profile = new Profile(new Dimension(20,20));
        final RGBLedStrip ledStrip=new RGBLedStrip(new Dimension(null,null,null));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(20);
        ledStrip.getDimension().width=199;

        final ComposedProduct composedProduct = new ComposedProduct(300,null);
        composedProduct.addProducts(profile);
        composedProduct.addProducts(ledStrip);

        ksession.insert( composedProduct );
        ksession.insert(profile);
        ksession.insert(ledStrip);

        // and fire the rules
        ksession.fireAllRules();

        System.out.println(messages);
        // Remove comment if using logging
        // logger.close();

        // and then dispose the session
        ksession.dispose();
    }



}
