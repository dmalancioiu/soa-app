package com.example.catalog;

import java.util.UUID;

public class CatalogItem {
  private String id;
  private String name;
  private int stock;

  public CatalogItem() {
    this.id = UUID.randomUUID().toString();
  }

  public CatalogItem(String name, int stock) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.stock = stock;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getStock() {
    return stock;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }
}
