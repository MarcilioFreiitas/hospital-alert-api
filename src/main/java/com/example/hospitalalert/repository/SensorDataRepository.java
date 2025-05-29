package com.example.hospitalalert.repository;

import com.example.hospitalalert.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    // Métodos customizados podem ser declarados aqui, se necessário.
}
