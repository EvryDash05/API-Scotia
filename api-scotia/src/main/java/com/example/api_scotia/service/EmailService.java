package com.example.api_scotia.service;

import java.util.Map;

public interface EmailService {
    void sendEmail(String type, String to, Map<String, Object> parameters);
}
