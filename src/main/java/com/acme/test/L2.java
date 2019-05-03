package com.acme.test;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import java.util.Random;

@ApplicationScoped
public class L2 {

    public void listen(@ObservesAsync String msg) {
        System.out.println("L2 got a message: " + msg);
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("And L2 returned");
    }
}
