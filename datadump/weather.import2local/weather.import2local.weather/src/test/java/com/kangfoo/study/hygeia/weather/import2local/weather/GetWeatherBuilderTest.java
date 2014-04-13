package com.kangfoo.study.hygeia.weather.import2local.weather;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;

import org.junit.Test;

/**
 * mvn clean test -Dtest=com.kangfoo.study.hygeia.weather.import2local.weather.GetWeatherBuilderTest
 */
public class GetWeatherBuilderTest extends CamelBlueprintTestSupport {
	
    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/blueprint.xml";
    }

    @Test
    public void testRoute() throws Exception {
        // do nothing
        Thread.sleep(10 * 1000);
        // assert expectations
        assertMockEndpointsSatisfied();
    }

}
