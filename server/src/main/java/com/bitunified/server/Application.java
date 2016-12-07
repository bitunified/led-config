package com.bitunified.server;

import com.bitunified.ledconfig.ConfigResult;
import com.bitunified.ledconfig.LedConfig;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.message.Message;
import com.bitunified.server.message.ServerResponse;
import org.drools.core.impl.StatefulKnowledgeSessionImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Path("/engine")
public class Application {

        private LedConfig ledConfig=new LedConfig();
        @POST
        @Path("/data")
        @Produces(MediaType.APPLICATION_JSON)
        public ServerResponse input(@FormParam("ccNumber") String number) {
                ServerResponse result= new ServerResponse("yes");
                ConfigResult configResult=ledConfig.rules(new String[]{number});

                List<String> clientMessages=new ArrayList<String>();

                for (Message message:configResult.getMessages()){
                        clientMessages.add(message.getMessage());
                }
                result.setMessages(clientMessages.toArray(new String[]{}));
                result.setMessageMap(configResult.getMessageMap());
                return result;


        }


        @GET
        @Path("/health")
        @Produces(MediaType.TEXT_PLAIN)
        public String healthCheck() {
                return "ok";
        }

}
