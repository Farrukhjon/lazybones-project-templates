package org.farrukh.examples.rest

import org.farrukh.examples.rest.category.UnitTestCategory
import org.junit.experimental.categories.Category

/**
 * Base unit level test class based on spock specification.
 */
@Category(UnitTestCategory)
abstract class BaseUnitTest extends BaseTest {
}
