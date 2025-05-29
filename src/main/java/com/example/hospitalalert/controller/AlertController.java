package com.example.hospitalalert.controller;

import com.example.hospitalalert.model.SensorData;
import com.example.hospitalalert.service.AlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 // Libera requisições de qualquer origem
@Tag(name = "Alert", description = "Endpoints para gerenciamento de alertas")
@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/alert")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @Operation(summary = "Recebe dados do sensor", 
               description = "Processa os dados enviados, salva no banco e envia alerta se necessário.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dados recebidos e processados com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<String> receiveAlert(@RequestBody SensorData sensorData) {
        alertService.processSensorData(sensorData);
        return ResponseEntity.ok("Dados recebidos e processados");
    }

    @Operation(summary = "Retorna todos os alertas", 
               description = "Retorna a lista completa de alertas cadastrados no banco de dados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Alertas retornados com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<SensorData>> getAllAlerts() {
        List<SensorData> alerts = alertService.getAllAlerts();
        return ResponseEntity.ok(alerts);
    }
}
