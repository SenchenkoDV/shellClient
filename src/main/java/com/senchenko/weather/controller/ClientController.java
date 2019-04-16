package com.senchenko.weather.controller;

import com.senchenko.weather.service.WeatherService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sshd.shell.springboot.autoconfiguration.SshdShellCommand;

import java.io.IOException;

@Component
@SshdShellCommand(value = "temperature", description = "Show temperature")
public class ClientController {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private WeatherService weatherService;

    @SshdShellCommand(value = "city", description = "Show temperature in city")
    public String cityTemperature(String arg) throws IOException {
        String temperature = "No data for this city.";
        try {
            temperature = weatherService.getTemperature(arg);
        } catch (JSONException e) {
            LOGGER.log(Level.ERROR, "Сity output error");
        }
        return temperature;
    }
}
