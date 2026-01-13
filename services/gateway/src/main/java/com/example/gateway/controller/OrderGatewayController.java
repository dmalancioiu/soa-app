package com.example.gateway.controller;

import com.example.gateway.client.OrderClient;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderGatewayController {
  private final OrderClient orderClient;

  public OrderGatewayController(OrderClient orderClient) {
    this.orderClient = orderClient;
  }

  @GetMapping
  public List<Map<String, Object>> getOrders() {
    return orderClient.getOrders();
  }

  @PostMapping
  public Map<String, Object> createOrder(@RequestBody Map<String, Object> payload) {
    return orderClient.createOrder(payload);
  }
}
