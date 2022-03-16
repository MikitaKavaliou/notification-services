package by.kovaliov.notificationservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import by.kovaliov.notificationservice.model.Notification;
import by.kovaliov.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class NotificationsControllerImpl implements NotificationsController {

  private final NotificationService notificationService;

  @Override
  public List<Notification> findAll() {
    return notificationService.findAll();
  }

  @Override
  public Notification save(Notification notification) {
    return notificationService.save(notification);
  }
}