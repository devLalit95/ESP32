package com.example.ESP32.controller;

import com.example.ESP32.service.LedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/led")
@CrossOrigin(origins = "*") // Frontend se access ke liye
public class LedController {

    @Autowired
    private LedService ledService;

    @PostMapping("/on")
    public ResponseEntity<String> turnOn() {
        try {
            String result = ledService.turnOnLed();
            return ResponseEntity.ok()
                    .header("Content-Type", "text/plain")
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Server error: " + e.getMessage());
        }
    }

    @PostMapping("/off")
    public ResponseEntity<String> turnOff() {
        try {
            String result = ledService.turnOffLed();
            return ResponseEntity.ok()
                    .header("Content-Type", "text/plain")
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Server error: " + e.getMessage());
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        try {
            String result = ledService.getLedStatus();
            return ResponseEntity.ok()
                    .header("Content-Type", "text/plain")
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Server error: " + e.getMessage());
        }
    }

    @GetMapping("/info")
    public ResponseEntity<String> getSystemInfo() {
        try {
            String result = ledService.getSystemInfo();
            return ResponseEntity.ok()
                    .header("Content-Type", "text/plain")
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Server error: " + e.getMessage());
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> testConnection() {
        try {
            String result = ledService.testConnection();
            return ResponseEntity.ok()
                    .header("Content-Type", "text/plain")
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Server error: " + e.getMessage());
        }
    }

    // Optional: Toggle endpoint
    @PostMapping("/toggle")
    public ResponseEntity<String> toggleLed() {
        try {
            String currentStatus = ledService.getLedStatus();
            String result;

            if (currentStatus.toLowerCase().contains("on")) {
                result = ledService.turnOffLed();
            } else {
                result = ledService.turnOnLed();
            }

            return ResponseEntity.ok()
                    .header("Content-Type", "text/plain")
                    .body(result);

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Toggle error: " + e.getMessage());
        }
    }
}