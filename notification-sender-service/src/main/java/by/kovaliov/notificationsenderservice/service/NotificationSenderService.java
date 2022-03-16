package by.kovaliov.notificationsenderservice.service;

import by.kovaliov.notificationsenderservice.dto.NotificationDto;
import by.kovaliov.notificationsenderservice.dto.SendNotificationDto;
import by.kovaliov.notificationsenderservice.model.SendNotification;

public interface NotificationSenderService {

  boolean send(NotificationDto notificationDto, SendNotification sendNotification);
}
