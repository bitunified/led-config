package com.bitunified.server;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/engine")
public class Application {

        @POST
        @Path("/data")
        @Consumes(MediaType.MULTIPART_FORM_DATA)

        public Response input(@FormParam("ccNumber") String number) {
                return Response.status(200)
                        .entity("re")
                        .build();


        }


        @GET
        @Path("/health")
        @Produces(MediaType.TEXT_PLAIN)
        public String healthCheck() {
                return "ok";
        }

}
