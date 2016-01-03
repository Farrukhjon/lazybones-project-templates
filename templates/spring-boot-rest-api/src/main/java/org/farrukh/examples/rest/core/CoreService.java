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

import org.farrukh.examples.rest.inbound.domain.Greeting;

/**
 * Core service represents high-level abstraction.
 * Implementations should provide specific implementations.
 */
public interface CoreService {

    /**
     * The specific implementations should realize conversion for incoming greeting message.
     * @param greeting the incoming message.
     * @return the converted result of the incoming message.
     */
    Greeting convert(final Greeting greeting);

}
