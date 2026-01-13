package com.example.order;

import java.time.Instant;
import java.util.UUID;

public class Order {
  private String id;
  private String item;
  private int quantity;
  private Instant createdAt;

  public Order() {
    this.id = UUID.randomUUID().toString();
    this.createdAt = Instant.now();
  }

  public Order(String item, int quantity) {
    this();
    this.item = item;
    this.quantity = quantity;
  }

  public String getId() {
    return id;
  }

  public String getItem() {
    return item;
  }

  public int getQuantity() {
    return quantity;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
