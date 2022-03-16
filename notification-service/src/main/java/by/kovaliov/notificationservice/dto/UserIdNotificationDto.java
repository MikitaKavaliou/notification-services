package by.kovaliov.notificationservice.dto;

import by.kovaliov.notificationservice.model.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserIdNotificationDto {
  private long userId;
  private Notification notification;
}
