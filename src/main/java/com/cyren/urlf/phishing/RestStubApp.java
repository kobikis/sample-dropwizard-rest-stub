package com.cyren.urlf.phishing;

import com.cyren.urlf.phishing.configuration.RestStubConfig;
import com.cyren.urlf.phishing.resources.PersonService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class RestStubApp extends Application<RestStubConfig> {

	public static void main(String[] args) throws Exception {
		new RestStubApp().run(args);
	}

	@Override
	public void initialize(Bootstrap<RestStubConfig> bootstrap) {
		bootstrap.addBundle(new SwaggerBundle<RestStubConfig>() {
			@Override
			protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(RestStubConfig restStubConfig) {
				// this would be the preferred way to set up swagger, you can also construct the object here programtically if you want
				return restStubConfig.swaggerBundleConfiguration;
			}
		});
	}

	@Override
	public void run(RestStubConfig config, Environment env) {
		final PersonService personService = new PersonService();
		env.jersey().register(personService);

		env.healthChecks().register("template",
				new RestStubHealthCheck(config.getVersion()));
	}
}
