package com.bitunified.server;

import com.bitunified.ledconfig.ConfigResult;
import com.bitunified.ledconfig.LedConfig;
import com.bitunified.ledconfig.PriceCalculator;
import com.bitunified.ledconfig.domain.message.Message;
import com.bitunified.server.message.ServerResponse;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Path("/engine")
public class Application extends ResourceConfig {

public Application(){
    this.packages("com.bitunified.server")
            .register(ObjectMapperProvider.class)  // No need to register this provider if no special configuration is required.
            .register(JacksonFeature.class);
}

    @POST
    @Path("/data")
    @Produces(MediaType.APPLICATION_JSON)
    public ServerResponse input(@FormParam("productcode") String number) {
        LedConfig ledConfig = new LedConfig();
        PriceCalculator priceCalculator = new PriceCalculator();
        ServerResponse result = new ServerResponse("false");
        if (number != null && number.length() > 4) {
            try {
                ConfigResult configResult = ledConfig.rules(new String[]{number});
                List<String> clientMessages = new ArrayList<String>();
                BigDecimal totalPrice = priceCalculator.calculate(configResult);
                for (Message message : configResult.getMessages()) {

                    clientMessages.add(message.getMessage());
                }
                result.setMessages(clientMessages.toArray(new String[]{}));
                result.setMessageMap(configResult.getMessageMap());
                result.setTotalPrice(totalPrice.doubleValue());
                result.setPartList(configResult.getPartList());
                result.setInstructions(configResult.getInstructions());
                if (configResult.getMessageMap().isEmpty() && configResult.getMessages().isEmpty()) {
                    result.setSuccess("false");
                    result.setTotalPrice(0d);
                }

            } finally {
                ledConfig.dispose();
            }
        } else {
            result = new ServerResponse("no");
        }
        return result;


    }


    @GET
    @Path("/health")
    @Produces(MediaType.TEXT_PLAIN)
    public String healthCheck() {
        return "ok";
    }

}
