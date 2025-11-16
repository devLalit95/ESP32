package com.example.ESP32.controller;

import com.example.ESP32.service.LedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class LedController {

    private final LedService ledService;
    private static final Logger logger = LoggerFactory.getLogger(LedController.class);

    public LedController(LedService ledService) {
        this.ledService = ledService;
    }

    @GetMapping("/led/on")
    public String ledOn() {
        logger.info("LED ON request received");
        String result = ledService.turnOn();
        logger.info("LED status after ON request: {}", result);
        return "LED turned ON. Current status: " + result;
    }

    @GetMapping("/led/off")
    public String ledOff() {
        logger.info("LED OFF request received");
        String result = ledService.turnOff();
        logger.info("LED status after OFF request: {}", result);
        return "LED turned OFF. Current status: " + result;
    }

    @GetMapping("/led/status")
    public String ledStatus() {
        String status = ledService.getStatus();
        logger.info("LED status requested: {}", status);
        return "Current LED status: " + status;
    }
}
