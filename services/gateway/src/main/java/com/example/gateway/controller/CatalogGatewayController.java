package com.example.gateway.controller;

import com.example.gateway.client.CatalogClient;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/catalog")
public class CatalogGatewayController {
  private final CatalogClient catalogClient;

  public CatalogGatewayController(CatalogClient catalogClient) {
    this.catalogClient = catalogClient;
  }

  @GetMapping
  public List<Map<String, Object>> getCatalog() {
    return catalogClient.getCatalog();
  }
}
