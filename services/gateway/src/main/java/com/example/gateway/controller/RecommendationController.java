package com.example.gateway.controller;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {
  private final LambdaClient lambdaClient;
  private final String functionName;

  public RecommendationController(
      @Value("${aws.endpoint}") String endpoint,
      @Value("${aws.region}") String region,
      @Value("${aws.access-key}") String accessKey,
      @Value("${aws.secret-key}") String secretKey,
      @Value("${aws.lambda.function:order-recommendation}") String functionName) {
    this.lambdaClient = LambdaClient.builder()
        .endpointOverride(java.net.URI.create(endpoint))
        .region(Region.of(region))
        .credentialsProvider(StaticCredentialsProvider.create(
            AwsBasicCredentials.create(accessKey, secretKey)))
        .build();
    this.functionName = functionName;
  }

  @GetMapping
  public Map<String, Object> getRecommendations() {
    var request = InvokeRequest.builder()
        .functionName(functionName)
        .payload(SdkBytes.fromUtf8String("{}"))
        .build();
    String payload = lambdaClient.invoke(request)
        .payload()
        .asString(StandardCharsets.UTF_8);
    return Map.of("recommendations", payload);
  }
}
