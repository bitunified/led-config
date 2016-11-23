package com.bitunified.server;

import com.bitunified.ledconfig.LedConfig;
import com.bitunified.ledconfig.domain.message.Message;
import com.bitunified.server.message.ServerResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/engine")
public class Application {

        private LedConfig ledConfig=new LedConfig();
        @POST
        @Path("/data")
        @Produces(MediaType.APPLICATION_JSON)
        public ServerResponse input(@FormParam("ccNumber") String number) {
                ServerResponse result= new ServerResponse("yes");
                List<Message> messages=ledConfig.rules(null);
                List<String> clientMessages=new ArrayList<String>();
                for (Message message:messages){
                        clientMessages.add(message.getMessage());
                }
                result.setMessages(clientMessages.toArray(new String[]{}));
                return result;


        }


        @GET
        @Path("/health")
        @Produces(MediaType.TEXT_PLAIN)
        public String healthCheck() {
                return "ok";
        }

}
