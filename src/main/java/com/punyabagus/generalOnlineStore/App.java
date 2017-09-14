package com.punyabagus.generalOnlineStore;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by prasojo on 9/14/17.
 */
public class App extends ResourceConfig {
    public App() {
        packages(true, "com.punyabagus.generalOnlineStore");
        register(new Binder());
    }
}
