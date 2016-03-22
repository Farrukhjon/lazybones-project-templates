package org.farrukh.examples.spring.boot

import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration

/**
 * Base class for integration tests via application context loading.
 */
@ContextConfiguration(classes = Application, loader = SpringApplicationContextLoader)
class BaseIntegrationTest extends BaseTest {
}
