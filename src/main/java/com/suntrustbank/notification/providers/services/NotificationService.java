package com.suntrustbank.notification.providers.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.suntrustbank.notification.providers.dtos.enums.MessageType;

public interface NotificationService {
    void send(JsonNode body, MessageType messageType) throws Exception;
}
