package com.acme.test;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.NotificationOptions;
import javax.inject.Inject;
import javax.ws.rs.*;
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
    }

    @GET
    @Path("send")
    @PermitAll
    public Response sendSomething(@QueryParam("useDefault") @DefaultValue("false") boolean useDefault) {
        System.out.println("Will fire an event. Using default executor: " + useDefault);


        if (useDefault) {
            no = NotificationOptions.builder()
                    .set("weld.async.notification.mode", "PARALLEL")
                    .build();
        } else {
            ExecutorService thisOneWorks = Executors.newFixedThreadPool(5);
            no = NotificationOptions.builder()
                    .set("weld.async.notification.mode", "PARALLEL")
                    .setExecutor(thisOneWorks)
                    .build();
        }



        CompletionStage<String> cs = this.event.fireAsync("this is an event", no).whenComplete((msg, t) -> {
            if (t != null) {
                System.out.println("Stage failed with: " + t);
            } else {
                System.out.println("Stage completed ok!!!");
            }
        });
        System.out.println("We have a cs: " + cs);
        return Response.ok("OK").build();
    }
}
