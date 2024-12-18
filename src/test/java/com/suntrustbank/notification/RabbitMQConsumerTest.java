package com.suntrustbank.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suntrustbank.notification.core.utils.Constants;
import com.suntrustbank.notification.providers.dtos.EmailRequest;
import com.suntrustbank.notification.providers.dtos.SmsRequest;
import com.suntrustbank.notification.providers.dtos.enums.SMSType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashMap;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext
class RabbitMQConsumerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @Value("${spring.rabbitmq.template.default-receive-queue}")
//    private String queueName; // Make sure this matches your consumer's queue

    @Test
    void testEmailConsumer() throws InterruptedException, JsonProcessingException {
        // Create a test EmailRequest
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setFrom("test@example.com");
        emailRequest.setTo("receiver@example.com");
        HashMap<String, Object> body = new HashMap<>();
        body.put("subject", "Test Subject");
        body.put("message", "Test Message");
        emailRequest.setBody(body);

        // Send message to RabbitMQ
        rabbitTemplate.convertAndSend(Constants.EMAIL_ROUTING_KEY, objectMapper.writeValueAsString(emailRequest));

        // Wait to ensure the consumer processes the message
        Thread.sleep(2000);

        // Assertions or validations (depends on your consumer's behavior)
        // E.g., if your consumer writes to a database, validate the DB entry here
    }

    @Test
    void testSmsConsumer() throws InterruptedException, JsonProcessingException {
        // Create a test SmsRequest
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setFrom("12345");
        smsRequest.setTo("67890");
        smsRequest.setSmsType(SMSType.OTP);
        smsRequest.setBody("Test SMS Body");

        // Send message to RabbitMQ
        rabbitTemplate.convertAndSend(Constants.SMS_ROUTING_KEY, objectMapper.writeValueAsString(smsRequest));

        // Wait to ensure the consumer processes the message
        Thread.sleep(2000);

        // Assertions or validations (depends on your consumer's behavior)
    }
}
