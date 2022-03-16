package by.kovaliov.notificationservice.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import by.kovaliov.notificationservice.model.Notification;

public interface NotificationsController {

  @GetMapping(path = "/v1/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
  List<Notification> findAll();

  @PostMapping(path = "/v1/notifications", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Notification save(@RequestBody Notification notification);
}
