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
package org.farrukh.examples.rest.core

import static org.farrukh.examples.rest.feedback.RestFeedbackContext.GREETING_CONVERSION_WARNING
import org.farrukh.examples.rest.BaseUnitTest
import org.farrukh.examples.rest.exception.ConversationWarning
import org.farrukh.examples.rest.inbound.domain.Greeting

/**
 * Unit level test for the core service.
 */
class CoreServiceUnitTest extends BaseUnitTest {

    /**
     * Given service under test.
     */
    CoreService sut

    def setup() {
        sut = new DefaultCoreService()
    }

    def 'exercise happy path converting incoming message to the upper case'() {
        given: 'some incoming greeting message'
        def incomingMessage = new Greeting(message: 'hello world!')

        and: 'the expected message'
        def expectedMessage = 'HELLO WORLD!'

        when: 'the actual conversation is made'
        def resultMessage = sut.convert(incomingMessage)

        then: 'the converted result is returned'
        resultMessage.message == expectedMessage
    }

    def 'exercise sad path converting incoming message to the upper case'() {
        given: 'some empty greeting message'
        def emptyMessage = ''
        def incomingMessage = new Greeting(message: emptyMessage)

        and: 'the expected warning message'
        def expectedWarningMessage = GREETING_CONVERSION_WARNING.formatString

        when: 'the actual conversation is made'
        sut.convert(incomingMessage)

        then: 'the converted result is returned'
        def warning = thrown(ConversationWarning)
        warning.message == expectedWarningMessage
    }

}
