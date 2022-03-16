package by.kovaliov.notificationservice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.kovaliov.notificationservice.dto.UserIdNotificationDto;
import by.kovaliov.notificationservice.service.NotificationService;
import by.kovaliov.notificationservice.service.QueueConsumerService;
import by.kovaliov.notificationservice.service.QueueProducerService;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class QueueConsumerServiceImpl implements QueueConsumerService {

  private final QueueProducerService producerService;
  private final NotificationService notificationService;

  @Transactional
  @Override
  public void receiveMessage(Long userId) {
    producerService.produce(new UserIdNotificationDto(userId, notificationService.pollNotificationByUserId(userId)));
  }
}
