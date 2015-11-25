package org.farrukh.examples.integration;

import org.farrukh.examples.integration.handler.GreetingHeaderEnricher;
import org.farrukh.examples.integration.handler.GreetingMessageFilterSelector;
import org.farrukh.examples.integration.handler.GreetingMessagesRouter;
import org.farrukh.examples.integration.handler.GreetingServiceActivatorByAnnotation;
import org.farrukh.examples.integration.handler.GreetingTransformerByImplementation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.integration.amqp.inbound.AmqpInboundChannelAdapter;
import org.springframework.integration.amqp.outbound.AmqpOutboundEndpoint;
import org.springframework.integration.amqp.support.AmqpHeaderMapper;
import org.springframework.integration.amqp.support.DefaultAmqpHeaderMapper;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.filter.MessageFilter;
import org.springframework.integration.filter.MethodInvokingSelector;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.handler.MessageHandlerChain;
import org.springframework.integration.handler.ServiceActivatingHandler;
import org.springframework.integration.mapping.AbstractHeaderMapper;
import org.springframework.integration.router.MethodInvokingRouter;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.MessageTransformingHandler;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.MessageProcessingHeaderValueMessageProcessor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Main endpoint and startup point.
 */
@SuppressWarnings("javadocmethod")
@SpringBootApplication
@EnableIntegration
@EnableConfigurationProperties(ApplicationProperties.class)
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Jackson2JsonObjectMapper jsonObjectMapper() {
        return new Jackson2JsonObjectMapper(Jackson2ObjectMapperBuilder.json().build());
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(final ConnectionFactory connectionFactory,
                                                                   final ApplicationProperties configuration) {
        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        messageListenerContainer.setQueueNames(configuration.getQueueName());
        return messageListenerContainer;
    }

    @Bean
    public Queue queue(final ApplicationProperties configuration) {
        return new Queue(configuration.getQueueName());
    }

    @Bean
    public FanoutExchange exchange(final ApplicationProperties configuration) {
        return new FanoutExchange(configuration.getExchangeName(), configuration.isRabbitExchangeDurable(), false);
    }

    @Bean
    public Binding binding(final Queue queue, final FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(final ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public GreetingTransformerByImplementation greetingTransformer() {
        return new GreetingTransformerByImplementation();
    }

    @Bean
    public GreetingServiceActivatorByAnnotation greetingServiceActivator() {
        return new GreetingServiceActivatorByAnnotation();
    }

    @Bean
    public AmqpOutboundEndpoint amqpOutboundEndpoint(final AmqpTemplate rabbitTemplate,
                                                     final ApplicationProperties configuration) {
        AmqpOutboundEndpoint amqpOutboundEndpoint = new AmqpOutboundEndpoint(rabbitTemplate);
        amqpOutboundEndpoint.setExchangeName(configuration.getExchangeName());
        amqpOutboundEndpoint.setRoutingKey(configuration.getQueueName());
        return amqpOutboundEndpoint;
    }

    @Bean
    MessageChannel outputChannel(final AmqpOutboundEndpoint amqpOutboundEndpoint) {
        DirectChannel channel = new DirectChannel();
        channel.subscribe(amqpOutboundEndpoint);
        return channel;
    }

    @Bean
    public GreetingHeaderEnricher greetingHeaderEnricher() {
        return new GreetingHeaderEnricher();
    }

    @SuppressWarnings("linelength")
    @Bean
    public HeaderEnricher headerEnricher() {
        // TODO Improve long name
        MessageProcessingHeaderValueMessageProcessor messageProcessor = new MessageProcessingHeaderValueMessageProcessor(greetingHeaderEnricher(), "handleHeaders");
        HashMap<String, HeaderValueMessageProcessor<?>> map = new HashMap<String, HeaderValueMessageProcessor<?>>();
        map.put("media-type", messageProcessor);
        return new HeaderEnricher(map);
    }

    @Bean
    public LoggingHandler loggingHandler() {
        return new LoggingHandler("INFO");
    }

    @Bean
    public MessageChannel ignoredEventsChannel() {
        DirectChannel channel = new DirectChannel();
        channel.subscribe(loggingHandler());
        return channel;
    }

    @Bean
    public MessageFilter greetingMessageFilter() {
        MessageFilter messageFilter = new MessageFilter(
                new MethodInvokingSelector(new GreetingMessageFilterSelector(), "handleMessage"));
        messageFilter.setDiscardChannel(ignoredEventsChannel());
        return messageFilter;
    }

    @Bean
    public GreetingMessagesRouter greetingMessagesRouter() {
        return new GreetingMessagesRouter();
    }

    @Bean
    public MethodInvokingRouter methodInvokingRouter(final GreetingMessagesRouter greetingMessagesRouter) {
        return new MethodInvokingRouter(greetingMessagesRouter);
    }

    @Bean
    public MessageHandlerChain greetingChain(final HeaderEnricher headerEnricher,
                                             final GreetingTransformerByImplementation greetingTransformer,
                                             final MessageFilter greetingMessageFilter,
                                             final GreetingServiceActivatorByAnnotation greetingServiceActivator,
                                             final MethodInvokingRouter methodInvokingRouter) {
        MessageHandlerChain chain = new MessageHandlerChain();
        List<MessageHandler> handlers = new ArrayList<MessageHandler>();
        handlers.add(new MessageTransformingHandler(headerEnricher));
        handlers.add(new MessageTransformingHandler(greetingTransformer));
        handlers.add(greetingMessageFilter);
        handlers.add(new ServiceActivatingHandler(greetingServiceActivator));
        handlers.add(methodInvokingRouter);
        chain.setHandlers(handlers);
        return chain;
    }

    @Bean
    public MessageChannel inputChannel(final MessageHandlerChain greetingChain) {
        DirectChannel inputChannel = new DirectChannel();
        inputChannel.subscribe(greetingChain);
        return inputChannel;
    }

    @Bean
    public AmqpHeaderMapper customHeaderMapper() {
        DefaultAmqpHeaderMapper headerMapper = new DefaultAmqpHeaderMapper();
        headerMapper.setRequestHeaderNames(AbstractHeaderMapper.NON_STANDARD_HEADER_NAME_PATTERN);
        headerMapper.setReplyHeaderNames(AbstractHeaderMapper.STANDARD_REPLY_HEADER_NAME_PATTERN);
        return headerMapper;
    }

    @Bean
    public AmqpInboundChannelAdapter inboundChannelAdapter(final AbstractMessageListenerContainer msgListenerContainer,
                                                           final MessageChannel inputChannel,
                                                           final MessageChannel errorChannel,
                                                           final AmqpHeaderMapper customHeaderMapper) {
        AmqpInboundChannelAdapter inboundChannelAdapter = new AmqpInboundChannelAdapter(msgListenerContainer);
        inboundChannelAdapter.setOutputChannel(inputChannel);
        inboundChannelAdapter.setErrorChannel(errorChannel);
        inboundChannelAdapter.setHeaderMapper(customHeaderMapper);
        return inboundChannelAdapter;
    }

}
