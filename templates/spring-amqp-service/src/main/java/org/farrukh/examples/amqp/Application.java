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

package org.farrukh.examples.amqp;

import org.farrukh.examples.amqp.inbound.CustomMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Spring boot based stand-alone application.
 */
@SuppressWarnings({"checkstyle:hideutilityclassconstructor", "checkstyle:constantname"})
@EnableConfigurationProperties(ApplicationProperties.class)
@SpringBootApplication
public class Application {

    /**
     * Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadocstyle"})
    public static void main(final String[] args) {
        logger.info("Running entry point application");
        SpringApplication.run(Application.class, args);
    }

    /**
     * Simple message listener container
     * @param connectionFactory - RabbitMQ connection factory.
     * @param configuration - Custom application information.
     * @param messageListener - The message listener with delegate object in the adapter.
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(final ConnectionFactory connectionFactory,
                                                                   final ApplicationProperties configuration,
                                                                   final MessageListener messageListener) {
        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        messageListenerContainer.addQueueNames(configuration.getQueueName());
        messageListenerContainer.setMessageListener(messageListener);
        return messageListenerContainer;
    }

    /**
     * Custom message handler.
     * @return - message handler.
     */
    @Bean
    public CustomMessageHandler messageHandler() {
        return new CustomMessageHandler();
    }

    /**
     * Custom message listener.
     * @param messageHandler an message object handler.
     * @return message listener adapter
     */
    @Bean
    public MessageListener messageListener(final CustomMessageHandler messageHandler) {
        return new MessageListenerAdapter(messageHandler);
    }

    /**
     * RabbitMQ message queue.
     * @param configuration - queue name holder object.
     * @return - message queue.
     */
    @Bean
    public Queue queue(final ApplicationProperties configuration) {
        return new Queue(configuration.getQueueName());
    }

    /**
     * RabbitMQ message exchange.
     * @param configuration - exchange name holder object.
     * @return - direct message exchange.
     */
    @Bean
    public Exchange exchange(final ApplicationProperties configuration) {
        return new DirectExchange(configuration.getExchangeName());
    }

    /**
     * Binds the message queue via direct message exchange.
     * @param queue - the message queue bean.
     * @param exchange - the message exchange bean.
     * @return - message binding.
     */
    @Bean
    public Binding binding(final Queue queue, final DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).withQueueName();
    }

}
