package com.suntrustbank.notification.providers.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suntrustbank.notification.core.utils.Constants;
import com.suntrustbank.notification.providers.dtos.enums.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQConsumerService {

    private final NotificationService notificationService;

    @RabbitListener(queues = Constants.SMS_QUEUE_NAME, concurrency = Constants.CONCURRENCY_RANGE)
    public void processSmsMessage(String message) {
        try {
            JsonNode body = new ObjectMapper().readTree(message);
            notificationService.send(body, MessageType.SMS);
        } catch (Exception e) {
            log.error("Failed to process SMS message: {}. Error:: {}", message, e.getMessage());
        }
    }

    @RabbitListener(queues = Constants.EMAIL_QUEUE_NAME, concurrency = Constants.CONCURRENCY_RANGE)
    public void processEmailMessage(String message) {
        try {
            JsonNode body = new ObjectMapper().readTree(message);
            notificationService.send(body, MessageType.EMAIL);
        } catch (Exception e) {
            log.info("Failed to process Email message: {}. Error:: {}", message, e.getMessage());
        }
    }
}
