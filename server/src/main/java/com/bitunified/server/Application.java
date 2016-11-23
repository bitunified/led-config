package com.bitunified.server;

import com.bitunified.ledconfig.LedConfig;
import com.bitunified.ledconfig.domain.message.Message;
import com.bitunified.server.message.ServerResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/engine")
public class Application {

        private LedConfig ledConfig=new LedConfig();
        @POST
        @Path("/data")
        @Produces(MediaType.APPLICATION_JSON)
        public ServerResponse input(@FormParam("ccNumber") String number) {
                ServerResponse result= new ServerResponse("yes");
                List<Message> messages=ledConfig.execute(null);
                result.setMessages(new String[]{"1","2"});
                return result;


        }


        @GET
        @Path("/health")
        @Produces(MediaType.TEXT_PLAIN)
        public String healthCheck() {
                return "ok";
        }

}
