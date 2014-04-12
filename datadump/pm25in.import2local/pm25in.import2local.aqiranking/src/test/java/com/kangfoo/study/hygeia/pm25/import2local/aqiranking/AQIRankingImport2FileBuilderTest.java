package com.kangfoo.study.hygeia.pm25.import2local.aqiranking;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-6
 * Time: 上午12:40
 * mvn test -Dtest=com.kangfoo.study.hygeia.pm25.import2local.aqiranking.AQIRankingImport2FileBuilderTest
 */
public class AQIRankingImport2FileBuilderTest extends CamelBlueprintTestSupport {

    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/blueprint.xml";
    }

    @Test
    public void testRoute() throws Exception {
        // do nothing
        Thread.sleep(10 * 1000);
    }

}
