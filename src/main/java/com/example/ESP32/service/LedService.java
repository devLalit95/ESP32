package com.example.ESP32.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;


import java.time.Duration;

@Service
public class LedService {

    private static final Logger logger = LoggerFactory.getLogger(LedService.class);

    // ESP32 IP
    private final String ESP32_IP = "http://192.168.1.100";

    private final WebClient webClient;

    public LedService() {
        this.webClient = WebClient.builder()
                .baseUrl(ESP32_IP)
                .build();
    }

    public String turnOn() {
        try {
            logger.info("Sending request to turn LED ON");
            String response = webClient.get()
                    .uri("/on")
                    .retrieve()
                    .bodyToMono(String.class)
                    .timeout(Duration.ofSeconds(5))  // 10 sec timeout
                    .block();  // Block to wait for response (simple for now)

            logger.info("Response from ESP32: {}", response);
            return "LED turned ON successfully: " + response;

        } catch (WebClientResponseException e) {
            logger.warn("ESP32 returned error: {}", e.getMessage());
            return "Error: ESP32 returned error - " + e.getMessage();
        } catch (Exception e) {
            logger.warn("Cannot connect to ESP32: {}", e.getMessage());
            return "Warning: Cannot connect to ESP32. Please check if it is online.";
        }
    }

    public String turnOff() {
        try {
            logger.info("Sending request to turn LED OFF");
            String response = webClient.get()
                    .uri("/off")
                    .retrieve()
                    .bodyToMono(String.class)
                    .timeout(Duration.ofSeconds(10))
                    .block();

            logger.info("Response from ESP32: {}", response);
            return "LED turned OFF successfully: " + response;

        } catch (WebClientResponseException e) {
            logger.warn("ESP32 returned error: {}", e.getMessage());
            return "Error: ESP32 returned error - " + e.getMessage();
        } catch (Exception e) {
            logger.warn("Cannot connect to ESP32: {}", e.getMessage());
            return "Warning: Cannot connect to ESP32. Please check if it is online.";
        }
    }

    public String getStatus() {
        try {
            logger.info("Fetching LED status from ESP32");
            String response = webClient.get()
                    .uri("/")
                    .retrieve()
                    .bodyToMono(String.class)
                    .timeout(Duration.ofSeconds(10))
                    .block();

            logger.info("ESP32 LED status: {}", response);
            return "Current LED status: " + response;

        } catch (WebClientResponseException e) {
            logger.warn("ESP32 returned error: {}", e.getMessage());
            return "Error: ESP32 returned error - " + e.getMessage();
        } catch (Exception e) {
            logger.warn("Cannot connect to ESP32: {}", e.getMessage());
            return "Warning: Cannot fetch LED status. ESP32 not reachable.";
        }
    }
}
