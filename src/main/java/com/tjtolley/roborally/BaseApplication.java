package com.tjtolley.roborally;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tjtolley.roborally.game.GameManager;
import com.tjtolley.roborally.resources.LobbyResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BaseApplication extends Application<WebConfig> {

    public static void main(String[] args) throws Exception {
        new BaseApplication().run(args);
    }

    @Override
    public String getName() {
        return "RoboRally";
    }

    @Override
    public void initialize(Bootstrap<WebConfig> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(WebConfig configuration,
            Environment environment) {
        Injector injector = Guice.createInjector();
        final GameResource resource = new GameResource(configuration.getTemplate(), configuration.getDefaultName());
        final LobbyResource lobby = injector.getInstance(LobbyResource.class);
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
        environment.jersey().register(lobby);
    }

    private static class GuiceModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(GameManager.class).asEagerSingleton();
        }

    }
}
