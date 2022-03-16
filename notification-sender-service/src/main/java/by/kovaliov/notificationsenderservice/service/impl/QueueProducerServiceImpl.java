package by.kovaliov.notificationsenderservice.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import by.kovaliov.notificationsenderservice.service.QueueProducerService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QueueProducerServiceImpl implements QueueProducerService {

  @Value("${spring.rabbitmq.queue.producer.exchange}")
  private String notificationRequestFanoutExchangeName;

  private final RabbitTemplate rabbitTemplate;

  @Override
  public void produce(long userId) {
    rabbitTemplate.setExchange(notificationRequestFanoutExchangeName);
    rabbitTemplate.convertAndSend(userId);
  }
}
