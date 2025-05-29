package com.example.hospitalalert.service;

import com.example.hospitalalert.model.SensorData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MessagingServiceImpl implements MessagingService {

    @Value("${notification.destinationEmail}")
    private String destinationEmail;
    
    private final JavaMailSender mailSender;

    public MessagingServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    @Override
    @Async
    public void sendEmailAlert(SensorData sensorData) {
        String subject = "Alerta Hospitalar Crítico";
        String text = "Condições críticas detectadas:\n" +
                      "Temperatura: " + sensorData.getTemperature() + " °C\n" +
                      "Umidade: " + sensorData.getHumidity() + " %\n" +
                      "Nível de Gás: " + sensorData.getGasValue() + "\n" +
                      "Sensores em alerta: " + sensorData.getAlertSensors() + "\n" +
                      "Data: " + sensorData.getData() + "\n" +
                      "Hora: " + sensorData.getHora();
        
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(destinationEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        
        mailSender.send(mailMessage);
        System.out.println("Email de alerta enviado para " + destinationEmail);
    }
}
