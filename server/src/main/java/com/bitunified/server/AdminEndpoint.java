package com.bitunified.server;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/admin")
public class AdminEndpoint extends ConfigApplication {


    @POST
    @Path("/start")
    public Response submitConfiguration() throws IOException {

        String message = updateData();

        return Response.status(200)
                .entity(message)
                .build();
    }


}
