package com.acme.test;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import java.util.Random;

@ApplicationScoped
public class L3 {

    public void listen(@ObservesAsync String msg) {
        System.out.println("L3 got a message: " + msg);
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("And L3 returned");
    }
}
