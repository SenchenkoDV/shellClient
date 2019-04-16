package com.senchenko.weather.service;


import com.senchenko.weather.entity.WeatherRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class WeatherService {
    private static final String RESULT = "City: %s, temperature = %s \n";

    @Value("${integration.url}")
    private static String integrationUrl = "http://integration:8081/integration/temperature";

    public String getTemperature(String cityName) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        WeatherRequest weatherRequest = new WeatherRequest();
        weatherRequest.setName(cityName);
        HttpEntity<WeatherRequest> request = new HttpEntity<>(weatherRequest);
        ResponseEntity<String> response = restTemplate
                .exchange(integrationUrl, HttpMethod.POST, request, String.class);
        JSONObject jsonBody = new JSONObject(Objects.requireNonNull(response.getBody()));
        JSONObject cityResponse = jsonBody.getJSONObject("ns2:getCityResponse");
        JSONObject city = cityResponse.getJSONObject("ns2:city");
        return String.format(RESULT, city.get("ns2:name"), city.get("ns2:temperature"));
    }
}
