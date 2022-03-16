package by.kovaliov.notificationsenderservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import by.kovaliov.notificationsenderservice.model.SendNotification;
import by.kovaliov.notificationsenderservice.service.SendNotificationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SendNotificationControllerImpl implements SendNotificationController {

  private final SendNotificationService sendNotificationService;

  @Override
  public SendNotification save(SendNotification sendNotification) {
    return sendNotificationService.create(sendNotification);
  }

  @Override
  public List<SendNotification> findAll() {
    return sendNotificationService.findAll();
  }

  @Override
  public SendNotification findById(long id) {
    return sendNotificationService.findById(id);
  }
}
