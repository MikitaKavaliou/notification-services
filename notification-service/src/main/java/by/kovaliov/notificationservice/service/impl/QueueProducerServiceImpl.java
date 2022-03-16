package by.kovaliov.notificationservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import by.kovaliov.notificationservice.dto.UserIdNotificationDto;
import by.kovaliov.notificationservice.service.QueueProducerService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QueueProducerServiceImpl implements QueueProducerService {

  @Value("${spring.rabbitmq.queue.producer.exchange}")
  private String notificationResponseFanoutExchangeName;

  private final RabbitTemplate rabbitTemplate;

  private static final Logger LOGGER = LoggerFactory.getLogger(QueueProducerServiceImpl.class);

  @Override
  public void produce(UserIdNotificationDto userIdNotificationListDto) {
    rabbitTemplate.setExchange(notificationResponseFanoutExchangeName);
    try {
      rabbitTemplate.convertAndSend(new ObjectMapper().writeValueAsString(userIdNotificationListDto));
    } catch (JsonProcessingException e) {
      LOGGER.error("Error writing list");
    }
  }
}
