package com.acme.test;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;

@ApplicationScoped
public class L3 {

    public void listen(@ObservesAsync String msg) {
        System.out.println("L3 got a message: " + msg);

        System.out.println("And L3 returned");
    }
}
