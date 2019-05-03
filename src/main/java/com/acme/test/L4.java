package com.acme.test;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;

@ApplicationScoped
public class L4 {

    public void listen(@ObservesAsync String msg) {
        System.out.println("L4 got a message: " + msg);

        System.out.println("And L4 returned");
    }
}
