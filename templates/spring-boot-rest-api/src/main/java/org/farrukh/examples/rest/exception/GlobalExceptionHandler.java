/*
 * Copyright (c) 2015. Farrukhjon D. Sattorov firedandy@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.farrukh.examples.rest.exception;

import org.farrukh.examples.rest.inbound.domain.metadata.ErrorResponse;
import org.kurron.feedback.FeedbackAware;
import org.kurron.feedback.FeedbackProvider;
import org.kurron.feedback.NullFeedbackProvider;
import org.kurron.feedback.exceptions.AbstractError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler implements FeedbackAware {

    /**
     * Provides non-feedback.
     */
    private FeedbackProvider nullFeedbackProvider = new NullFeedbackProvider();

    @Override
    public FeedbackProvider getFeedbackProvider() {
        return nullFeedbackProvider;
    }

    @Override
    public void setFeedbackProvider(final FeedbackProvider aProvider) {
        this.nullFeedbackProvider = aProvider;
    }

    /**
     * Handles an error belonging to the application.
     *
     * @param error the type of the error.
     * @return the response error.
     */
    @ExceptionHandler(AbstractError.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(final AbstractError error) {
        return new ResponseEntity<>(new ErrorResponse(error.getCode(), error.getMessage()), error.getHttpStatus());
    }

}
