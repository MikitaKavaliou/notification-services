package by.kovaliov.notificationsenderservice.service;

import by.kovaliov.notificationsenderservice.dto.NotificationDto;

public interface QueueConsumerService {

  void receiveMessage(String message);
}
