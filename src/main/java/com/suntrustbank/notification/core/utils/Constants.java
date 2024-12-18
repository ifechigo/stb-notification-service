package com.suntrustbank.notification.core.utils;

import lombok.Getter;

@Getter
public class Constants {
    public static final String NOTIFICATION_EXCHANGE_NAME = "notification.exchange";
    public static final String SMS_ROUTING_KEY = "sms";
    public static final String EMAIL_ROUTING_KEY = "email";
    public static final String SMS_QUEUE_NAME = "sms.queue";
    public static final String EMAIL_QUEUE_NAME = "email.queue";
    public static final String SMS_DLQ_ROUTING_KEY = "sms.dlq";
    public static final String EMAIL_DLQ_ROUTING_KEY = "email.dlq";
    public static final String X_DL_EXCHANGE = "x-dead-letter-exchange";
    public static final String X_DL_ROUTING_KEY = "x-dead-letter-routing-key";
    public static final String CONCURRENCY_RANGE = "5-30";
}
