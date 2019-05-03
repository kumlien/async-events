package com.acme.test;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@ApplicationScoped
public class L5 {

    private Client client;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
    }

    public void listen(@ObservesAsync String msg) {
        System.out.println("L5 got a message: " + msg);

        System.out.println("And L5 returned");
    }
}
