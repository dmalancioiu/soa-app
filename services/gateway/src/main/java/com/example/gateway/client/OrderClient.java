package com.example.gateway.client;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderClient {
  private final RestTemplate restTemplate;
  private final String baseUrl;

  public OrderClient(RestTemplate restTemplate, @Value("${order.service.url}") String baseUrl) {
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
  }

  public List<Map<String, Object>> getOrders() {
    return restTemplate.getForObject(baseUrl + "/orders", List.class);
  }

  public Map<String, Object> createOrder(Map<String, Object> payload) {
    return restTemplate.postForObject(baseUrl + "/orders", payload, Map.class);
  }
}
