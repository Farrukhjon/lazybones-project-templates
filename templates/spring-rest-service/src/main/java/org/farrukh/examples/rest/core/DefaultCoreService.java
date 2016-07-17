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

package org.farrukh.examples.rest.core;

import static org.farrukh.examples.rest.feedback.RestFeedbackContext.GREETING_CONVERSION_WARNING;
import org.farrukh.examples.rest.exception.ConversationWarning;
import org.farrukh.examples.rest.inbound.domain.Greeting;
import org.kurron.feedback.AbstractFeedbackAware;

/**
 * Default implementation of the service.
 */
public class DefaultCoreService extends AbstractFeedbackAware implements CoreService {

    @Override
    public Greeting convert(final Greeting greeting) {
        Greeting resultGreeting = new Greeting();
        String message = greeting.getMessage();
        if (message.isEmpty()) {
            getFeedbackProvider().sendFeedback(GREETING_CONVERSION_WARNING);
            throw new ConversationWarning(GREETING_CONVERSION_WARNING);
        }
        resultGreeting.setMessage(message.toUpperCase());
        return resultGreeting;
    }

}
