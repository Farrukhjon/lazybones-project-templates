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

package org.farrukh.examples.amqp.inbound.domain;

/**
 * Object holds a bytes payload.
 */
public class Payload {

    /**
     * the data in bytes.
     */
    private byte[] data;

    /**
     * Returns the bytes payload.
     * @return the data in bytes.
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Sets a butes payload.
     * @param data some data in bytes.
     */
    public void setData(final byte[] data) {
        this.data = data;
    }
}
