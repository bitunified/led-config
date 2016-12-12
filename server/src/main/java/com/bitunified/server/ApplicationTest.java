package com.bitunified.server;

import com.bitunified.server.message.ServerResponse;
import org.junit.Assert;
import org.junit.Test;


public class ApplicationTest {

    @Test
    public void test(){
        Application application=new Application();
        ServerResponse response=application.input("B13714D02000219a");
        Assert.assertEquals(response.getTotalPrice(),new Double(36));
    }
}