package com.example.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderPublisher {
  private static final Logger logger = LoggerFactory.getLogger(OrderPublisher.class);
  private final RabbitTemplate rabbitTemplate;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public OrderPublisher(RabbitTemplate rabbitTemplate, KafkaTemplate<String, String> kafkaTemplate) {
    this.rabbitTemplate = rabbitTemplate;
    this.kafkaTemplate = kafkaTemplate;
  }

  public void publishOrder(Order order) {
    String payload = String.format("{\"id\":\"%s\",\"item\":\"%s\",\"quantity\":%d}",
        order.getId(), order.getItem(), order.getQuantity());
    rabbitTemplate.convertAndSend("order.commands", payload);
    kafkaTemplate.send("order.events", payload);
    logger.info("Published order to RabbitMQ and Kafka: {}", payload);
  }
}
