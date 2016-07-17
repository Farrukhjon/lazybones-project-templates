package org.farrukh.examples.integration

import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.MessageBuilder
import org.springframework.amqp.core.MessageDeliveryMode
import org.springframework.amqp.core.MessagePropertiesBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import spock.util.concurrent.PollingConditions

/**
 * Created by Farrukhjon SATTOROV.
 */

class GreetingMessageFlowInboundIntegrationTest extends BaseInboundIntegrationTest {

    @Autowired
    RabbitAdmin rabbitAdmin

    @Autowired
    AmqpTemplate rabbitTemplate

    @Autowired
    FanoutExchange exchange

    @Autowired
    ApplicationProperties configuration

    def queueName

    def setup() {
        queueName = configuration.queueName
        assert rabbitTemplate
        rabbitAdmin.deleteQueue(queueName)
        def queue = new Queue(queueName, false, false, false)
        rabbitAdmin.declareQueue(queue)
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange))
    }

    def cleanup() {
        queueName = configuration.queueName
        rabbitAdmin.deleteQueue(queueName)
    }

    def 'should send greeting to Rabbitmq->inputChannel->AmqpInbound->transformer->outputChannel->AmqpOutbound-Rabbitmq->receive'() {
        given: 'a string payload'
        def payload = 'Hello world!'

        and: 'expected data are created'
        //def expectedPayload = payload + ' has been transformed'
        def expectedContentType = MediaType.APPLICATION_JSON_VALUE
        def expectedAppId = UUID.randomUUID().toString()

        and: 'the message is create'
        def messageProperties = MessagePropertiesBuilder.newInstance()
                .setAppId(expectedAppId)
                .setTimestamp(new Date())
                .setContentType(expectedContentType)
                .setMessageId(UUID.randomUUID().toString())
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .setCorrelationId(UUID.randomUUID().toString().bytes)
                .build()
        def message = MessageBuilder.withBody(payload.bytes)
                .andProperties(messageProperties)
                .build()

        when: 'a configured message is send to the queue and exchange'
        rabbitTemplate.send(configuration.exchangeName, configuration.queueName, message)

        then: 'expected result is returned'
        new PollingConditions(timeout: 50, delay: 10).eventually {
            def result = rabbitTemplate.receive(configuration.queueName)
            result
            //result.body == expectedPayload.bytes
            result.messageProperties.appId == expectedAppId
            result.messageProperties.contentType == expectedContentType
        }
    }

}
