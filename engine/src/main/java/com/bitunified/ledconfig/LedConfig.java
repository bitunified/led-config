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

import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.composedproduct.ComposedProduct;
import com.bitunified.ledconfig.domain.message.Message;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;
import com.bitunified.ledconfig.domain.product.PCB.types.DecoLedStrip;
import com.bitunified.ledconfig.domain.product.cable.Cable;
import com.bitunified.ledconfig.domain.product.cover.types.ResinClear;
import com.bitunified.ledconfig.domain.product.profile.Profile;
import com.bitunified.ledconfig.parts.Part;
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
        rules(args);
    }
    public static final List<Message> rules(final String[] args) {
        // KieServices is the factory for all KIE services 
        KieServices ks = KieServices.Factory.get();
        
        // From the kie services, a container is created from the classpath
        KieContainer kc = ks.getKieClasspathContainer();

        return execute( kc );

    }

    public static List<Message> execute( KieContainer kc ) {
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

        final Profile profile = new Profile(new Dimension(100,200));
        profile.setName("L20");
        //final RGBLedStrip ledStrip=new RGBLedStrip(new Dimension(null,null,null));
        final DecoLedStrip ledStrip=new DecoLedStrip(new Dimension(100));
        LedStrip.SECTION.setValue(10);

        ledStrip.getProperty(DecoLedStrip.COLOR).setValue("ROOD");

        final ResinClear resin=new ResinClear(null);

        final Cable cable=new Cable(new Dimension(4000));
        Cable.TYPE.setValue("2-aderig");

        final ComposedProduct composedProduct = new ComposedProduct(300,null);
        Part profilePart = new Part(profile);

        composedProduct.addProducts(profilePart);
        Part ledStripPart=new Part(ledStrip);
        composedProduct.addProducts(ledStripPart);


        ksession.insert(composedProduct);
        ksession.insert(profile);
        ksession.insert(ledStrip);
        ksession.insert(resin);
        ksession.insert(cable);
        // and fire the rules
        ksession.fireAllRules();

        for (Message message:messages){

            System.out.println(message);
        }
        // Remove comment if using logging
        // logger.close();

        // and then dispose the session
        ksession.dispose();
        return messages;
    }



}
