package com.example.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {
  private static final Logger logger = LoggerFactory.getLogger(OrderEventListener.class);
  private final SimpMessagingTemplate messagingTemplate;

  public OrderEventListener(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  @KafkaListener(topics = "order.events", groupId = "notifications")
  public void onOrderEvent(String event) {
    logger.info("Received order event: {}", event);
    messagingTemplate.convertAndSend("/topic/orders", event);
  }
}
