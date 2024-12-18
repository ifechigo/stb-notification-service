package com.suntrustbank.notification.providers.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class EmailRequest {
    private String from;
    private String to;
    private HashMap body;
}
