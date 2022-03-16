package by.kovaliov.notificationsenderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserIdNotificationDto {
  private long userId;
  private NotificationDto notification;
}
