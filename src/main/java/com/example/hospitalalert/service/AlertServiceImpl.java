package com.example.hospitalalert.service;

import com.example.hospitalalert.model.SensorData;
import com.example.hospitalalert.repository.SensorDataRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {

    private final MessagingService messagingService;
    private final SensorDataRepository sensorDataRepository;
    
    public AlertServiceImpl(MessagingService messagingService, SensorDataRepository sensorDataRepository) {
        this.messagingService = messagingService;
        this.sensorDataRepository = sensorDataRepository;
    }
    
    @Override
    public void processSensorData(SensorData sensorData) {
        // Assegura que data e hora estão preenchidos
        if(sensorData.getData() == null) {
            sensorData.setData(LocalDate.now());
        }
        if(sensorData.getHora() == null) {
            sensorData.setHora(LocalTime.now());
        }
        
        // Se o campo alertSensors não estiver preenchido, calcula qual(is) sensor(es) dispararam o alerta
        if (sensorData.getAlertSensors() == null || sensorData.getAlertSensors().trim().isEmpty()) {
            String computedAlerts = "";
            if (sensorData.getTemperature() != null && sensorData.getTemperature() > 30.0) {
                computedAlerts += "temperature";
            }
            if (sensorData.getHumidity() != null && sensorData.getHumidity() > 50.0) {
                if (!computedAlerts.isEmpty()) {
                    computedAlerts += ", ";
                }
                computedAlerts += "humidity";
            }
            if (sensorData.getGasValue() != null && sensorData.getGasValue() > 1200) {
                if (!computedAlerts.isEmpty()) {
                    computedAlerts += ", ";
                }
                computedAlerts += "gasValue";
            }
            sensorData.setAlertSensors(computedAlerts);
        }
        
        // Salva os dados no banco de dados
        sensorDataRepository.save(sensorData);
        
        // Verifica os thresholds para disparar o alerta
        boolean triggerAlert = (sensorData.getTemperature() > 30.0) ||
                               (sensorData.getHumidity()  > 50.0) ||
                               (sensorData.getGasValue()  > 1200);
        
        // Se algum dos thresholds for ultrapassado, dispara o alerta por e-mail
        if (triggerAlert) {
            messagingService.sendEmailAlert(sensorData);
        }
    }
    
    @Override
    public List<SensorData> getAllAlerts() {
        return sensorDataRepository.findAll();
    }
}
