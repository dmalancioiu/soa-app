package com.example.order;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderMessagingConfig {
  @Bean
  public Queue orderCommandsQueue() {
    return new Queue("order.commands", true);
  }
}
