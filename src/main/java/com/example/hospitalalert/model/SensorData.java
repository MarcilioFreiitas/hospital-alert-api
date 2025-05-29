package com.example.hospitalalert.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Float temperature;
    private Float humidity;
    private Integer gasValue;
    
    // Novo campo para armazenar qual(is) sensor(es) acionaram o alarme
    private String alertSensors;
    
    // Campos que serão preenchidos automaticamente na persistência
    private LocalDate data;
    private LocalTime hora;
    
    @PrePersist
    public void prePersist() {
        this.data = LocalDate.now();
        this.hora = LocalTime.now();
    }
    
    // Construtor auxiliar para conveniência (note que incluímos o alertSensors)
    public SensorData(Float temperature, Float humidity, Integer gasValue, String alertSensors) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.gasValue = gasValue;
        this.alertSensors = alertSensors;
    }

   
    public SensorData() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	public Float getHumidity() {
		return humidity;
	}

	public void setHumidity(Float humidity) {
		this.humidity = humidity;
	}

	public Integer getGasValue() {
		return gasValue;
	}

	public void setGasValue(Integer gasValue) {
		this.gasValue = gasValue;
	}

	public String getAlertSensors() {
		return alertSensors;
	}

	public void setAlertSensors(String alertSensors) {
		this.alertSensors = alertSensors;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
    
   
}
