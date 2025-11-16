package com.example.ESP32.controller;

import com.example.ESP32.service.DummyLedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dummy/led")
@CrossOrigin(origins = "*")
public class DummyLedController {

    private final DummyLedService dummyLedService;
    private static final Logger logger = LoggerFactory.getLogger(DummyLedController.class);

    public DummyLedController(DummyLedService dummyLedService) {
        this.dummyLedService = dummyLedService;
    }


    @GetMapping("/on")
    public String ledOn() {
        logger.info("📱 Android: LED ON request received");
        return dummyLedService.turnOn();
    }

    @GetMapping("/off")
    public String ledOff() {
        logger.info("📱 Android: LED OFF request received");
        return dummyLedService.turnOff();
    }

    @GetMapping("/status")
    public String ledStatus() {
        logger.info("📱 Android: Status request received");
        return dummyLedService.getStatus();
    }

    @GetMapping("/toggle")
    public String toggleLed() {
        logger.info("📱 Android: Toggle request received");
        return dummyLedService.toggle();
    }

    @PostMapping("/brightness/{level}")
    public String setBrightness(@PathVariable int level) {
        logger.info("📱 Android: Set brightness to {}%", level);
        return dummyLedService.setBrightness(level);
    }

    @PostMapping("/color")
    public String setColor(@RequestParam String color) {
        logger.info("📱 Android: Set color to {}", color);
        return dummyLedService.setColor(color);
    }

    @GetMapping("/info")
    public String getInfo() {
        logger.info("📱 Android: Detailed info requested");
        return dummyLedService.getDetailedInfo();
    }

    @GetMapping("/test")
    public String testConnection() {
        logger.info("📱 Android: Test connection received");
        return "✅ Dummy LED Controller is running!\n" +
                "🎛️ Mode: Full Simulation\n" +
                "🔗 Status: Ready to accept commands\n" +
                "📡 Endpoints: /on, /off, /status, /toggle, /brightness/{level}, /color?color=#HEX";
    }
}