package by.kovaliov.notificationservice.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import by.kovaliov.notificationservice.dto.UserIdNotificationDto;
import by.kovaliov.notificationservice.service.QueueProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueueProducerServiceImpl implements QueueProducerService {

  @Value("${spring.rabbitmq.queue.producer.exchange}")
  private String notificationResponseFanoutExchangeName;

  private final RabbitTemplate rabbitTemplate;

  @Override
  public void produce(UserIdNotificationDto userIdNotificationListDto) {
    rabbitTemplate.setExchange(notificationResponseFanoutExchangeName);
    try {
      rabbitTemplate.convertAndSend(new ObjectMapper().writeValueAsString(userIdNotificationListDto));
    } catch (JsonProcessingException e) {
      log.error("Error writing list");
    }
  }
}
