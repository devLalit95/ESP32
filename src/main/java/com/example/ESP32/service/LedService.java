package com.example.ESP32.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.http.ResponseEntity;

@Service
public class LedService {

    private static final Logger logger = LoggerFactory.getLogger(LedService.class);

    // Yaha aapka ESP32 ka IP dalo
    private final String ESP32_BASE_URL = "http://10.133.172.116";

    private final RestTemplate restTemplate;

    public LedService() {
        this.restTemplate = new RestTemplate();
        // Timeout set karna ho to ye use karo:
        /*
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        this.restTemplate = new RestTemplate(factory);
        */
    }

    public String turnOnLed() {
        try {
            String url = ESP32_BASE_URL + "/on";
            logger.info("🚀 LED ON request bhej raha hoon: {}", url);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String responseBody = response.getBody();

            logger.info("✅ LED ON successful: {}", responseBody);
            return responseBody;

        } catch (HttpClientErrorException e) {
            logger.error("❌ HTTP Error LED ON: {} - {}", e.getStatusCode(), e.getMessage());
            return "HTTP Error " + e.getStatusCode() + ": " + e.getMessage();
        } catch (ResourceAccessException e) {
            logger.error("🌐 Connection Failed LED ON: {}", e.getMessage());
            return "ESP32 connection failed: " + e.getMessage();
        } catch (Exception e) {
            logger.error("❌ Unexpected Error LED ON: {}", e.getMessage());
            return "Error: " + e.getMessage();
        }
    }

    public String turnOffLed() {
        try {
            String url = ESP32_BASE_URL + "/off";
            logger.info("🚀 LED OFF request bhej raha hoon: {}", url);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String responseBody = response.getBody();

            logger.info("✅ LED OFF successful: {}", responseBody);
            return responseBody;

        } catch (HttpClientErrorException e) {
            logger.error("❌ HTTP Error LED OFF: {} - {}", e.getStatusCode(), e.getMessage());
            return "HTTP Error " + e.getStatusCode() + ": " + e.getMessage();
        } catch (ResourceAccessException e) {
            logger.error("🌐 Connection Failed LED OFF: {}", e.getMessage());
            return "ESP32 connection failed: " + e.getMessage();
        } catch (Exception e) {
            logger.error("❌ Unexpected Error LED OFF: {}", e.getMessage());
            return "Error: " + e.getMessage();
        }
    }

    public String getLedStatus() {
        try {
            String url = ESP32_BASE_URL + "/status";
            logger.info("🚀 LED Status request bhej raha hoon: {}", url);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String responseBody = response.getBody();

            logger.info("📊 LED Status received: {}", responseBody);
            return responseBody;

        } catch (HttpClientErrorException e) {
            logger.error("❌ HTTP Error Status: {} - {}", e.getStatusCode(), e.getMessage());
            return "HTTP Error " + e.getStatusCode() + ": " + e.getMessage();
        } catch (ResourceAccessException e) {
            logger.error("🌐 Connection Failed Status: {}", e.getMessage());
            return "ESP32 connection failed: " + e.getMessage();
        } catch (Exception e) {
            logger.error("❌ Unexpected Error Status: {}", e.getMessage());
            return "Error: " + e.getMessage();
        }
    }

    public String getSystemInfo() {
        try {
            String url = ESP32_BASE_URL + "/info";
            logger.info("🚀 System Info request bhej raha hoon: {}", url);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String responseBody = response.getBody();

            logger.info("📋 System Info received: {}", responseBody);
            return responseBody;

        } catch (HttpClientErrorException e) {
            logger.error("❌ HTTP Error System Info: {} - {}", e.getStatusCode(), e.getMessage());
            return "HTTP Error " + e.getStatusCode() + ": " + e.getMessage();
        } catch (ResourceAccessException e) {
            logger.error("🌐 Connection Failed System Info: {}", e.getMessage());
            return "ESP32 connection failed: " + e.getMessage();
        } catch (Exception e) {
            logger.error("❌ Unexpected Error System Info: {}", e.getMessage());
            return "Error: " + e.getMessage();
        }
    }

    public String testConnection() {
        try {
            logger.info("🧪 ESP32 Connection test kar raha hoon: {}", ESP32_BASE_URL);

            ResponseEntity<String> response = restTemplate.getForEntity(ESP32_BASE_URL, String.class);
            String responseBody = response.getBody();

            logger.info("✅ Connection test successful: {}", responseBody);
            return "ESP32 Connected! Response: " + responseBody;

        } catch (HttpClientErrorException e) {
            logger.error("❌ HTTP Error Connection Test: {}", e.getStatusCode());
            return "HTTP Error " + e.getStatusCode() + " - ESP32 responded with error";
        } catch (ResourceAccessException e) {
            logger.error("🌐 Connection Test Failed: {}", e.getMessage());
            return "Cannot connect to ESP32 at " + ESP32_BASE_URL + " - " + e.getMessage();
        } catch (Exception e) {
            logger.error("❌ Connection Test Failed - Unexpected: {}", e.getMessage());
            return "Connection test failed: " + e.getMessage();
        }
    }
}