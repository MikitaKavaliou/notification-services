package by.kovaliov.notificationsenderservice.service;

import java.util.List;

import by.kovaliov.notificationsenderservice.model.SendNotification;

public interface SendNotificationService {

  SendNotification create(SendNotification sendNotification);

  List<SendNotification> findAll();

  SendNotification findById(long id);

  SendNotification findByUserId(long userId);

  void update(SendNotification sendNotification);
}
