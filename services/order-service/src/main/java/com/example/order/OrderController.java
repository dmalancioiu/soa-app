package com.example.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
  private final List<Order> orders = new CopyOnWriteArrayList<>();
  private final OrderPublisher publisher;

  public OrderController(OrderPublisher publisher) {
    this.publisher = publisher;
  }

  @GetMapping
  public List<Order> getOrders() {
    return new ArrayList<>(orders);
  }

  @PostMapping
  public Order createOrder(@RequestBody Map<String, Object> payload) {
    String item = (String) payload.getOrDefault("item", "Unknown");
    int quantity = ((Number) payload.getOrDefault("quantity", 1)).intValue();
    Order order = new Order(item, quantity);
    orders.add(order);
    publisher.publishOrder(order);
    return order;
  }
}
