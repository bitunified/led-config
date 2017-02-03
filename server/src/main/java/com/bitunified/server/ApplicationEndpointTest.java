package com.bitunified.server;

import com.bitunified.server.message.ServerResponse;
import org.junit.Assert;
import org.junit.Test;


public class ApplicationEndpointTest {

    @Test
    public void test(){
        ApplicationEndpoint applicationEndpoint =new ApplicationEndpoint();
        ServerResponse response= applicationEndpoint.input("B13714D02000219a");
        Assert.assertEquals(response.getTotalPrice(),new Double(36));
    }
}