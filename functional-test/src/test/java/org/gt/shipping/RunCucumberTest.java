package org.gt.shipping;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/org/gt/shipping/"},
        plugin = {"pretty", "html:target/cucumber"})
public class RunCucumberTest {
}
