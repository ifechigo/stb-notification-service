package com.suntrustbank.notification.core.configs.rabbitmq;

import com.suntrustbank.notification.core.utils.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    @Bean
    public TopicExchange notificationExchange() {
        return ExchangeBuilder.topicExchange(Constants.NOTIFICATION_EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    @Bean
    public Queue smsQueue() {
        return QueueBuilder.durable(Constants.SMS_QUEUE_NAME)
                .withArgument(Constants.X_DL_EXCHANGE, Constants.NOTIFICATION_EXCHANGE_NAME)
                .withArgument(Constants.X_DL_ROUTING_KEY, Constants.SMS_DLQ_ROUTING_KEY)
                .build();
    }

    @Bean
    public Queue emailQueue() {
        return QueueBuilder.durable(Constants.EMAIL_QUEUE_NAME)
                .withArgument(Constants.X_DL_EXCHANGE, Constants.NOTIFICATION_EXCHANGE_NAME)
                .withArgument(Constants.X_DL_ROUTING_KEY, Constants.EMAIL_DLQ_ROUTING_KEY)
                .build();
    }

    @Bean
    public Queue smsDeadLetterQueue() {
        return QueueBuilder.durable(Constants.SMS_DLQ_ROUTING_KEY).build();
    }

    @Bean
    public Queue emailDeadLetterQueue() {
        return QueueBuilder.durable(Constants.EMAIL_DLQ_ROUTING_KEY).build();
    }

    @Bean
    public Binding smsBinding(Queue smsQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(smsQueue)
                .to(notificationExchange)
                .with(Constants.SMS_ROUTING_KEY);
    }

    @Bean
    public Binding emailBinding(Queue emailQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(emailQueue)
                .to(notificationExchange)
                .with(Constants.EMAIL_ROUTING_KEY);
    }

    @Bean
    public Binding smsDLQBinding(Queue smsDeadLetterQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(smsDeadLetterQueue)
                .to(notificationExchange)
                .with(Constants.SMS_DLQ_ROUTING_KEY);
    }

    @Bean
    public Binding emailDLQBinding(Queue emailDeadLetterQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(emailDeadLetterQueue)
                .to(notificationExchange)
                .with(Constants.EMAIL_DLQ_ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}
