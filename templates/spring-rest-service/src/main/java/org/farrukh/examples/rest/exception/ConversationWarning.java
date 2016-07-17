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
package org.farrukh.examples.rest.exception;

import org.kurron.feedback.FeedbackContext;
import org.kurron.feedback.exceptions.AbstractError;
import org.springframework.http.HttpStatus;

/**
 * Responsible for providing conversation exception.
 */
public class ConversationWarning extends AbstractError {

    public ConversationWarning(final FeedbackContext context,
                               final Object... arguments) {
        super(context, arguments);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getDeveloperMessage() {
        return "There is, at least an error on the request";
    }
}
