package org.farrukh.examples.rest

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

/**
 * Cucumber-JVM (Groovy) acceptance test runner.
 */
@CucumberOptions(
        features = ['classpath:org/farrukh/examples/rest/acceptance.feature'],
        plugin = ['pretty', 'html:build/reports/acceptanceTests'],
        monochrome = true,
        strict = true,
        tags = ['~@slow']
)
@RunWith(Cucumber)
class AcceptanceTests {
}
