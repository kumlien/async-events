package com.acme.test;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import java.util.Random;

@ApplicationScoped
public class L1 {

    public void listen(@ObservesAsync String msg) {
        System.out.println("L1 got a message: " + msg);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("And L1 returned");
    }
}
