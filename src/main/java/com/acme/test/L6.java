package com.acme.test;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.Random;

@ApplicationScoped
public class L6 {

    private Client client;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
    }

    public void listen(@ObservesAsync String msg) {
        System.out.println("L6 got a message: " + msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("And L6 returned");
    }
}
