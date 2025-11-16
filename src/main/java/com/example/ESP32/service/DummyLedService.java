package com.example.ESP32.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

@Service
public class DummyLedService {

    private static final Logger logger = LoggerFactory.getLogger(DummyLedService.class);
    private String ledState = "OFF";
    private int brightness = 0;
    private String color = "#FFFFFF";
    private boolean isConnected = true;
    private Random random = new Random();

    public String turnOn() {
        logger.info("🎛️ DUMMY: LED ON command received");
        ledState = "ON";
        brightness = 100;
        color = getRandomColor();

        String response = "✅ LED Strip turned ON\n" +
                "📍 Brightness: " + brightness + "%\n" +
                "🎨 Color: " + color + "\n" +
                "🔗 Status: Connected to Virtual LED Controller";

        logger.info("🎛️ DUMMY: {}", response);
        return response;
    }

    public String turnOff() {
        logger.info("🎛️ DUMMY: LED OFF command received");
        ledState = "OFF";
        brightness = 0;

        String response = "🔴 LED Strip turned OFF\n" +
                "📍 Brightness: " + brightness + "%\n" +
                "🔗 Status: Connected to Virtual LED Controller";

        logger.info("🎛️ DUMMY: {}", response);
        return response;
    }

    public String getStatus() {
        logger.info("🎛️ DUMMY: Status check requested");

        String response = "📊 LED Strip Status:\n" +
                "💡 Power: " + ledState + "\n" +
                "📍 Brightness: " + brightness + "%\n" +
                "🎨 Color: " + color + "\n" +
                "🔗 Controller: Virtual LED Simulator\n" +
                "⚡ Connection: Active";

        logger.info("🎛️ DUMMY: Status sent - {}", ledState);
        return response;
    }

    public String setBrightness(int level) {
        logger.info("🎛️ DUMMY: Set brightness to {}%", level);

        if (level < 0 || level > 100) {
            return "❌ Error: Brightness must be between 0-100%";
        }

        brightness = level;
        if (level > 0) {
            ledState = "ON";
        }

        return "✅ Brightness set to " + level + "%\n" +
                "💡 LED is now " + (level > 0 ? "ON" : "OFF");
    }

    public String setColor(String newColor) {
        logger.info("🎛️ DUMMY: Set color to {}", newColor);

        color = newColor;
        if (brightness == 0) {
            brightness = 80; // Auto turn on when color changed
            ledState = "ON";
        }

        return "✅ Color changed to " + newColor + "\n" +
                "💡 LED automatically turned ON\n" +
                "📍 Brightness: " + brightness + "%";
    }

    public String toggle() {
        logger.info("🎛️ DUMMY: Toggle command received");

        if ("ON".equals(ledState)) {
            return turnOff();
        } else {
            return turnOn();
        }
    }

    public String getDetailedInfo() {
        logger.info("🎛️ DUMMY: Detailed info requested");

        return "🎛️ VIRTUAL LED CONTROLLER\n" +
                "====================\n" +
                "💡 Power: " + ledState + "\n" +
                "📍 Brightness: " + brightness + "%\n" +
                "🎨 Color: " + color + "\n" +
                "🔗 Connection: Stable\n" +
                "🖥️ Mode: Simulation\n" +
                "📡 Protocol: Virtual HTTP\n" +
                "⚡ Last Command: Successful";
    }

    private String getRandomColor() {
        String[] colors = {
                "#FF0000", "#00FF00", "#0000FF",
                "#FFFF00", "#FF00FF", "#00FFFF",
                "#FFA500", "#800080", "#FFC0CB"
        };
        return colors[random.nextInt(colors.length)];
    }
}