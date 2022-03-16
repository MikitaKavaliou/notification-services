package by.kovaliov.notificationsenderservice.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.kovaliov.notificationsenderservice.dto.NotificationDto;
import by.kovaliov.notificationsenderservice.model.SendNotification;
import by.kovaliov.notificationsenderservice.service.NotificationSenderService;

@Service
public class NotificationSenderServiceSms implements NotificationSenderService {

  private static final Logger LOGGER = LoggerFactory.getLogger(NotificationSenderServiceSms.class);

  @Override
  public boolean send(NotificationDto notificationDto, SendNotification sendNotification) {
    LOGGER.info("SMS is not sent due to sender problems with topic={}, content={}, to user with id={}, phone={}.",
        notificationDto.getTopic(), notificationDto.getContent(), notificationDto.getUserId(), sendNotification.getAddress());
    return false;
  }
}
