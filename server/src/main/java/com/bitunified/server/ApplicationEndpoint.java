package com.bitunified.server;

import com.bitunified.ledconfig.ConfigResult;
import com.bitunified.ledconfig.LedConfig;
import com.bitunified.ledconfig.PriceCalculator;
import com.bitunified.ledconfig.configuration.parser.steps.ParseStep;
import com.bitunified.ledconfig.configuration.parser.steps.Parser;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.message.Message;
import com.bitunified.ledconfig.domain.message.MessageStatus;
import com.bitunified.ledconfig.parts.Part;
import com.bitunified.server.message.ServerResponse;
import com.bitunified.server.models.Models;
import com.bitunified.server.models.PartModelCollector;
import com.bitunified.server.models.Relations;
import com.bitunified.server.service.StepService;
import com.bitunified.server.steps.Steps;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Path("/engine")
public class ApplicationEndpoint {

    public ApplicationEndpoint() {

    }
    @GET
    @Path("/steps")
    @Produces(MediaType.APPLICATION_JSON)
    public Steps getAllStepss(){
        StepService stepService = new StepService();

        return stepService.getSteps();
    }

    @GET
    @Path("/models")
    @Produces(MediaType.APPLICATION_JSON)
    public Models getAllModels(){
        Models models = new Models();

        Parser parser = new Parser();

        parser.createParts();


        models.setModels(parser.getModels());
        return models;
    }

    @GET
    @Path("/relations")
    @Produces(MediaType.APPLICATION_JSON)
    public Relations getAllRelations(){

        Parser parser = new Parser();

        parser.createParts();

        Relations relations=new Relations();
        relations.setRelations(parser.getRelationDefinitions());
        return relations;
    }



    @POST
    @Path("/data")
    @Produces(MediaType.APPLICATION_JSON)
    public ServerResponse input(@FormParam("productcode") String number) {
        LedConfig ledConfig = new LedConfig();
        PriceCalculator priceCalculator = new PriceCalculator();
        ServerResponse result = new ServerResponse("true");
        if (number != null && number.length() > 4) {
            try {
                ConfigResult configResult = ledConfig.rules(new String[]{number});
                List<String> clientMessages = new ArrayList<String>();
                BigDecimal totalPrice = priceCalculator.calculate(configResult);
                for (Message message : configResult.getMessages()) {

                    clientMessages.add(message.getMessage());
                }
                result.setMessages(new HashSet<>(configResult.getMessages()));
                result.setMessageMap(configResult.getMessageMap());
                result.setTotalPrice(totalPrice.doubleValue());
                result.addPartList(configResult.getPartList());

                result.setInstructions(configResult.getInstructions());
                result.setParseSteps(configResult.getParseSteps());
                if (isThereAnError(configResult,totalPrice)) {
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

    private boolean isThereAnError(ConfigResult configResult,BigDecimal price) {
        for (Message m:configResult.getMessages()){
            if (m.getStatus()== MessageStatus.ERROR){
                return true;
            }
        }
        for (ParseStep step:configResult.getParseSteps()){
            if (step.isMantatory()&&(step.getModelResult()==null || step.getModelResult().getModel()==null || step.isError())){
                return true;
            }
        }
        return price.compareTo(BigDecimal.ZERO) <= 0;
    }


}
