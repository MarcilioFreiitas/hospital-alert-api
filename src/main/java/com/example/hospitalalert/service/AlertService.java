package com.example.hospitalalert.service;

import com.example.hospitalalert.model.SensorData;
import java.util.List;

public interface AlertService {
    void processSensorData(SensorData sensorData);
    
    // Novo método para retornar todas as informações
    List<SensorData> getAllAlerts();
}
