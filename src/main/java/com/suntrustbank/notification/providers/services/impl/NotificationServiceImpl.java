package com.suntrustbank.notification.providers.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suntrustbank.notification.providers.dtos.EmailRequest;
import com.suntrustbank.notification.providers.dtos.SmsRequest;
import com.suntrustbank.notification.providers.dtos.enums.MessageType;
import com.suntrustbank.notification.providers.dtos.enums.SMSType;
import com.suntrustbank.notification.providers.services.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final ObjectMapper objectMapper;

    public void send(JsonNode body, MessageType messageType) throws Exception {
        switch (messageType) {
            case SMS -> sendSMS(body);
            case EMAIL -> sendEMAIL(body);
            default -> {
                log.info("invalid notification type");
            }
        }
    }

    private void sendSMS(JsonNode body) {
        String to = null;
        String bodyMessage = null;
        try {
            SmsRequest smsRequest = objectMapper.treeToValue(body, SmsRequest.class);
            to = smsRequest.getTo();
            bodyMessage = smsRequest.getBody();

            if (SMSType.OTP.equals(smsRequest.getSmsType())) {

            } else if (SMSType.BALANCE.equals(smsRequest.getSmsType())) {

            }


            log.info("SMS PROCESSING ==> Sending {} to [{}] with message: [{}]", smsRequest.getSmsType().name(), to, bodyMessage);
        } catch (Exception e) {
            log.error("SMS ERROR ==> Failed to send sms to: [{}] with body: [{}]. Error: {}", to, bodyMessage, e.getMessage(), e);
        }
    }


    private void sendEMAIL(JsonNode body) {
        String to = null;
        String bodyMessage = null;
        try {
            EmailRequest smsRequest = objectMapper.treeToValue(body, EmailRequest.class);
            to = smsRequest.getTo();
            bodyMessage = smsRequest.getBody().toString();



            log.info("EMAIL PROCESSING ==> Sending email to [{}] with message: [{}]", to, bodyMessage);
        } catch (Exception e) {
            log.error("EMAIL ERROR ==> Failed to send email to: [{}] with body: [{}]. Error: {}", to, bodyMessage, e.getMessage(), e);
        }
    }
}
