package by.kovaliov.notificationservice.service;


import java.util.List;

import by.kovaliov.notificationservice.model.Notification;


public interface NotificationService {
  List<Notification> findAll();

  Notification save(Notification notification);
//
//  List<Notification> findNotificationByUserId(long userId);

  Notification pollNotificationByUserId(long userId);
}
