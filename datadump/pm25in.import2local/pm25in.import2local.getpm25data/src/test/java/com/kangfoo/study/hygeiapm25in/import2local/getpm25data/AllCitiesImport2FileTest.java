package com.kangfoo.study.hygeiapm25in.import2local.getpm25data;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

/**
 *  mvn clean test -Dtest=com.kangfoo.study.hygeiapm25in.import2local.getpm25data.AllCitiesImport2FileTest
 */
public class AllCitiesImport2FileTest extends CamelBlueprintTestSupport {

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
