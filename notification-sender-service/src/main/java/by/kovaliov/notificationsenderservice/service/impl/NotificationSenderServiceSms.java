package by.kovaliov.notificationsenderservice.service.impl;


import org.springframework.stereotype.Service;

import by.kovaliov.notificationsenderservice.dto.NotificationDto;
import by.kovaliov.notificationsenderservice.model.SendNotification;
import by.kovaliov.notificationsenderservice.service.NotificationSenderService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationSenderServiceSms implements NotificationSenderService {

  @Override
  public boolean send(NotificationDto notificationDto, SendNotification sendNotification) {
    log.info("SMS is not sent due to sender problems with topic={}, content={}, to user with id={}, phone={}.",
        notificationDto.getTopic(), notificationDto.getContent(), notificationDto.getUserId(), sendNotification.getAddress());
    return false;
  }
}
