package com.chat.rpg.service;

import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

@Service
public class AIService {
    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAIResponse(String userInput) {
        String apiUrl = "https://api.openai.com/v1/engines/davinci-codex/completions";
        JSONObject requestPayload = new JSONObject();
        requestPayload.put("prompt", userInput);
        requestPayload.put("max_tokens", 50);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(requestPayload.toString(), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);
        
        JSONObject jsonResponse = new JSONObject(response.getBody());
        return jsonResponse.getJSONArray("choices").getJSONObject(0).getString("text").trim();
    }
}
