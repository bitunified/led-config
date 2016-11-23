package com.bitunified.server;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/engine")
public class Application {

        @POST
        @Path("/data")
        @Produces(MediaType.TEXT_PLAIN)
        public String input() {
                return "ok";
        }


        @GET
        @Path("/health")
        @Produces(MediaType.TEXT_PLAIN)
        public String healthCheck() {
                return "ok";
        }

}
