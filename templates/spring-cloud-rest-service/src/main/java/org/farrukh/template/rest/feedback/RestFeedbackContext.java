/*
 * Copyright (c) 2015. Farrukhjon D. Sattorov firedandy@gmail.com.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.farrukh.template.rest.feedback;

import org.kurron.feedback.Audience;
import org.kurron.feedback.FeedbackContext;
import org.kurron.feedback.FeedbackLevel;

/**
 * Provide logging feedback for method operation observation.
 */
public enum RestFeedbackContext implements FeedbackContext {

    SOME_FEEDBACK(1000, "Some operation has been done!", FeedbackLevel.INFO, Audience.DEVELOPMENT),

    SECOND_FEEDBACK(1001, "Some operation is failed", FeedbackLevel.WARN, Audience.QA),

    THIRD_FEEDBACK(1002, "Some operation is success", FeedbackLevel.INFO, Audience.OPERATIONS);

    /**
     * Unique context code for this instance.
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

    RestFeedbackContext(final int theCode, final String theFormatString, final FeedbackLevel theFeedbackLevel,
                               final Audience theAudience) {
        this.theAudience = theAudience;
        this.theCode = theCode;
        this.theFormatString = theFormatString;
        this.theFeedbackLevel = theFeedbackLevel;
    }

    @Override
    public Audience getAudience() {
        return theAudience;
    }

    @Override
    public int getCode() {
        return theCode;
    }

    @Override
    public FeedbackLevel getFeedbackLevel() {
        return theFeedbackLevel;
    }

    @Override
    public String getFormatString() {
        return theFormatString;
    }

}
