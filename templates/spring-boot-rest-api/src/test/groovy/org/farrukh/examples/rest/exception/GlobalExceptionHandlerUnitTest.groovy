/*
 * Copyright (c) 2015. Farrukhjon D. Sattorov firedandy@gmail.com.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.farrukh.examples.rest.exception

import org.farrukh.examples.rest.BaseUnitTest
import org.farrukh.examples.rest.feedback.RestFeedbackContext
import org.kurron.feedback.exceptions.AbstractError
import org.kurron.feedback.exceptions.BadRequestError
import org.kurron.feedback.exceptions.NotFoundError
import spock.lang.Unroll

/**
 * Unit level test for the global exception handler.
 */
class GlobalExceptionHandlerUnitTest extends BaseUnitTest {

    def sut = new GlobalExceptionHandler()

    @Unroll
    def 'exercise happy path handling global errors for #clazz.simpleName'(Class clazz) {
        given: 'the set of the feedback context'
        def feedbackContext = randomEnum(RestFeedbackContext)
        def error = clazz.newInstance(feedbackContext) as AbstractError

        when: 'the errors is handled'
        def result = sut.handleApplicationException(error)

        then: 'the expected results are returned'
        result
        result.statusCode == error.httpStatus

        where: 'clazz gets from the following data list'
        clazz << [ConversationWarning, BadRequestError, NotFoundError]
    }

}
