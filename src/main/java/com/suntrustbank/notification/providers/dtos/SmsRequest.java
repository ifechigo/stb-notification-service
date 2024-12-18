package com.suntrustbank.notification.providers.dtos;

import com.suntrustbank.notification.providers.dtos.enums.SMSType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsRequest {
    private String from;
    private String to;
    private SMSType smsType;
    private String body;
}
