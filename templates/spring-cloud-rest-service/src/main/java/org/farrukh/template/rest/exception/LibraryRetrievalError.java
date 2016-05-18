/*
 * Copyright (C) F.D. Sattorov Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by F.D. Sattorov <firedandy@gmail.com>, May 2016.
 *
 */

package org.farrukh.template.rest.exception;

import org.kurron.feedback.FeedbackContext;
import org.kurron.feedback.exceptions.AbstractError;
import org.springframework.http.HttpStatus;

/**
 * Created by Farrukhjon D. Sattorov on 5/10/16.
 */
public class LibraryRetrievalError extends AbstractError {

    public LibraryRetrievalError(final FeedbackContext context, final Object... arguments) {
        super(context, arguments);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }

    @Override
    public String getDeveloperMessage() {
        return null;
    }
}
