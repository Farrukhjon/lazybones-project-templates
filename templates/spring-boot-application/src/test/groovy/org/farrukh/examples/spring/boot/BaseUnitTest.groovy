package org.farrukh.examples.spring.boot

import org.farrukh.examples.spring.boot.category.UnitTestCategory
import org.junit.experimental.categories.Category

/**
 * Base unit level class.
 */
@Category(UnitTestCategory)
abstract class BaseUnitTest extends BaseTest {
}
