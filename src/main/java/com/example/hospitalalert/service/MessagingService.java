package com.example.hospitalalert.service;

import com.example.hospitalalert.model.SensorData;

public interface MessagingService {
    void sendEmailAlert(SensorData sensorData);
}
