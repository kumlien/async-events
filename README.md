# async-events
test for async events in wildfly

Hi there,
This is a test app used to figure out why the async event mechanism didn't work for us when using the default weld executor
but did work when specifying a fixedThreadPool executor. We have six listeners but only four gets invoked.

#### Notes:
* It fails for those listeneres which uses a javax.ws.rs.client.Client and initializes this Client in a @PostConstruct (which 
executes in a thread provided by the executor of course)
* It fails in the class org.jboss.resteasy.plugins.providers.RegisterBuiltin when it uses the current thread to fetch Providers
as resources.
* Since we have two failing listeners the org.jboss.weld.event.ObserverNotifier throws a CompletionException without cause which 
makes it more a little bit harder to find the problem. 

#### Test
* Build and deploy the war file
* Use the controller listening on http://localhost:8080/async-events-0.0.1/test/controller/send?useDefault=true to trigger an event
* Query parameter useDefault is used to specify which Executor to use.
