package com.example.gateway.client;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CatalogClient {
  private final RestTemplate restTemplate;
  private final String baseUrl;

  public CatalogClient(RestTemplate restTemplate, @Value("${catalog.service.url}") String baseUrl) {
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
  }

  public List<Map<String, Object>> getCatalog() {
    return restTemplate.getForObject(baseUrl + "/catalog", List.class);
  }
}
