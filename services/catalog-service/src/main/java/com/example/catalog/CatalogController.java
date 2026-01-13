package com.example.catalog;

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
@RequestMapping("/catalog")
public class CatalogController {
  private final List<CatalogItem> catalog = new CopyOnWriteArrayList<>(
      List.of(new CatalogItem("Nova Headphones", 42), new CatalogItem("Stellar Mug", 18)));

  @GetMapping
  public List<CatalogItem> getCatalog() {
    return new ArrayList<>(catalog);
  }

  @PostMapping
  public CatalogItem addItem(@RequestBody Map<String, Object> payload) {
    String name = (String) payload.getOrDefault("name", "New Item");
    int stock = ((Number) payload.getOrDefault("stock", 0)).intValue();
    CatalogItem item = new CatalogItem(name, stock);
    catalog.add(item);
    return item;
  }
}
