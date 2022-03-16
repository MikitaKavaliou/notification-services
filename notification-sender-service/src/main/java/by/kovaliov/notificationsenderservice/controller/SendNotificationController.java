package by.kovaliov.notificationsenderservice.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import by.kovaliov.notificationsenderservice.model.SendNotification;

public interface SendNotificationController {

  @PostMapping(path = "/v1/send", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  SendNotification save(@RequestBody SendNotification sendNotification);

  @GetMapping(path = "/v1/send", produces = MediaType.APPLICATION_JSON_VALUE)
  List<SendNotification> findAll();

  @GetMapping(path = "/v1/send/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  SendNotification findById(@PathVariable long id);

}
