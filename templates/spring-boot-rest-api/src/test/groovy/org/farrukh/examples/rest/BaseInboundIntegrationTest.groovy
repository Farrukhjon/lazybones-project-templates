package org.farrukh.examples.rest

import com.fasterxml.jackson.databind.ObjectMapper
import org.farrukh.examples.rest.category.OutboundIntegrationTestCategory
import org.junit.experimental.categories.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestOperations

/**
 * Base integration test class based on spock specification and spring testing fetures.
 */
@Category(OutboundIntegrationTestCategory)
@WebIntegrationTest( randomPort = true )
@ContextConfiguration(classes = Application, loader = SpringApplicationContextLoader)
abstract class BaseInboundIntegrationTest extends BaseTest {

    @Autowired
    protected RestOperations restTemplate

    @Autowired
    protected ObjectMapper objectMapper

    @Value('${local.server.port}')
    int port
}
