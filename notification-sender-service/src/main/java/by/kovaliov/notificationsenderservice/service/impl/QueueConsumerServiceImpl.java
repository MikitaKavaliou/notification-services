package by.kovaliov.notificationsenderservice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import by.kovaliov.notificationsenderservice.dto.NotificationDto;
import by.kovaliov.notificationsenderservice.dto.UserIdNotificationDto;
import by.kovaliov.notificationsenderservice.model.SendNotification;
import by.kovaliov.notificationsenderservice.model.SendNotificationStatus;
import by.kovaliov.notificationsenderservice.model.SendNotificationType;
import by.kovaliov.notificationsenderservice.service.NotificationSenderService;
import by.kovaliov.notificationsenderservice.service.QueueConsumerService;
import by.kovaliov.notificationsenderservice.service.SendNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueueConsumerServiceImpl implements QueueConsumerService {

  private final NotificationSenderService notificationSenderServiceEmail;
  private final NotificationSenderService notificationSenderServiceSms;
  private final SendNotificationService sendNotificationService;

  @Transactional
  @Override
  public void receiveMessage(String message) {
    SendNotification sendNotification;
    try {
      UserIdNotificationDto userIdNotificationDto =
          new ObjectMapper().readValue(message, UserIdNotificationDto.class);
      sendNotification = sendNotificationService.findByUserId(userIdNotificationDto.getUserId());
      if (userIdNotificationDto.getNotification() != null) {
        sendNotification(userIdNotificationDto, sendNotification);
      } else {
        updateStatus(sendNotification, SendNotificationStatus.FAILED);
        log.info("Notification for user with id={} not found", sendNotification.getUserId());
      }
    } catch (JsonProcessingException e) {
      log.error("Error parsing json");
    }
  }

  private void sendNotification(UserIdNotificationDto userIdNotificationDto, SendNotification sendNotification) {
    NotificationDto notification = userIdNotificationDto.getNotification();

    NotificationSenderService senderService = selectSenderByType(sendNotification.getSendType());

    boolean sendResult = senderService.send(notification, sendNotification);
    updateStatus(sendNotification, sendResult ? SendNotificationStatus.COMPLETED : SendNotificationStatus.FAILED);
  }

  private void updateStatus(SendNotification sendNotification, SendNotificationStatus status) {
    sendNotification.setSendStatus(status);
    sendNotificationService.update(sendNotification);
  }

  private NotificationSenderService selectSenderByType(SendNotificationType sendNotificationType) {
    return sendNotificationType == SendNotificationType.EMAIL ? notificationSenderServiceEmail : notificationSenderServiceSms;
  }
}
