package com.cyren.urlf.phishing;

import com.codahale.metrics.health.HealthCheck;
import com.cyren.urlf.phishing.persistence.PersonDB;

public class RestStubHealthCheck extends HealthCheck {
	private final String version;

	public RestStubHealthCheck(String version) {
		this.version = version;
	}

	@Override
	protected HealthCheck.Result check() throws Exception {
		if (PersonDB.getCount() == 0) {
			return HealthCheck.Result.unhealthy("No persons in DB! Version: " +
					this.version);
		}
		return HealthCheck.Result.healthy("OK with version: " + this.version +
				". Persons count: " + PersonDB.getCount());
	}
}
