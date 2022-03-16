package by.kovaliov.notificationservice.service;

import by.kovaliov.notificationservice.dto.UserIdNotificationDto;

public interface QueueProducerService {

  void produce(UserIdNotificationDto notification);
}
