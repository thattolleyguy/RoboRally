package com.tjtolley.roborally;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BaseApplication extends Application<WebConfig>
{

	public static void main(String[] args) throws Exception
	{
		new BaseApplication().run(args);
	}

	@Override
	public String getName()
	{
		return "RoboRally";
	}

	@Override
	public void initialize(Bootstrap<WebConfig> bootstrap)
	{
		// nothing to do yet
	}

	@Override
	public void run(WebConfig configuration,
					Environment environment)
	{
		final GameResource resource = new GameResource(configuration.getTemplate(), configuration.getDefaultName());
		final TemplateHealthCheck healthCheck
								  = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);
	}
}
