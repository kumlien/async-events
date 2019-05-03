package com.acme.test;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.NotificationOptions;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@ApplicationScoped
@Path("controller")
@Produces("application/json")
public class Controller extends Application {

    NotificationOptions no;

    @Inject
    Event<String> event;


    @PostConstruct
    public void init() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        no = NotificationOptions.builder()
                .set("weld.async.notification.mode", "PARALLEL")
                //.setExecutor(executor)
                .build();
    }

    @GET
    @Path("send")
    @PermitAll
    public Response sendSomething() {
        System.out.println("Entering send");
        CompletionStage<String> cs = this.event.fireAsync("event", no).whenComplete((s, t) -> {
            if(t != null) {
                System.out.println("oops: " + t);
            } else {
                System.out.println("Stage completed with " + s);
            }
        });
        System.out.println("We have a cs: " + cs);
        return Response.ok("OK").build();
    }
}
