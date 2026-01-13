package com.example.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCommandListener {
  private static final Logger logger = LoggerFactory.getLogger(OrderCommandListener.class);

  @RabbitListener(queues = "order.commands")
  public void onOrderCommand(String message) {
    logger.info("Received order command: {}", message);
  }
}
