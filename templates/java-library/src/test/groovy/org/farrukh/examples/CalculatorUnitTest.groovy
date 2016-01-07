package org.farrukh.examples

import org.farrukh.examples.category.UnitTestCategory
import org.junit.experimental.categories.Category
import spock.lang.Specification

/**
 * Unit level test for the Calculator.
 */
@Category(UnitTestCategory)
class CalculatorUnitTest extends Specification {

    def 'should test add operation with integer parameters'() {
        given: 'subject under test'
        def sut = new Calculator()

        and: 'some data for test'
        def a = 1
        def b = 2

        and: 'expected value'
        def expectedResult = 3

        when: 'add method is called'
        def result = sut.add(a, b)

        then: 'expected result is returned'
        result == expectedResult
    }
}
