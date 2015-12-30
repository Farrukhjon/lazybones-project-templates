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

package org.farrukh.examples.rest.feedback;

import org.kurron.feedback.Audience;
import org.kurron.feedback.FeedbackContext;
import org.kurron.feedback.FeedbackLevel;

import static org.kurron.feedback.Audience.DEVELOPMENT;
import static org.kurron.feedback.Audience.QA;
import static org.kurron.feedback.FeedbackLevel.INFO;
import static org.kurron.feedback.FeedbackLevel.WARN;

/**
 * Example of the feedback context.
 */
public enum RestFeedbackContext implements FeedbackContext {

    /**
     * Used for sending greeting feedback.
     */
    GREETING_FEEDBACK(1000, "Hello world feedback is generated", INFO, DEVELOPMENT),

    /**
     * Feedback for warning the content length.
     */
    WRONG_CONTENT_LENGTH(1001, "The content length must not be 0", WARN, QA);

    /**
     * unique context code for this instance.
     */
    private final int theCode;

    /**
     * Message format string for this instance.
     */
    private final String theFormatString;

    /**
     * Feedback level for this instance.
     */
    private final FeedbackLevel theFeedbackLevel;

    /**
     * The audience for this instance.
     */
    private final Audience theAudience;

    /**
     * Creates the feedback context.
     * @param theCode the code for the identifying context.
     * @param theFormatString the context message.
     * @param theFeedbackLevel level of the context message.
     * @param theAudience the audience who is interesting the feedback.
     */
    RestFeedbackContext(final int theCode,
                        final String theFormatString,
                        final FeedbackLevel theFeedbackLevel,
                        final Audience theAudience) {
        this.theAudience = theAudience;
        this.theCode = theCode;
        this.theFormatString = theFormatString;
        this.theFeedbackLevel = theFeedbackLevel;
    }

    @Override
    public int getCode() {
        return theCode;
    }

    @Override
    public String getFormatString() {
        return theFormatString;
    }

    @Override
    public FeedbackLevel getFeedbackLevel() {
        return theFeedbackLevel;
    }

    @Override
    public Audience getAudience() {
        return theAudience;
    }
}
