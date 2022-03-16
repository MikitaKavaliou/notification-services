package by.kovaliov.notificationsenderservice.dto;

import java.util.List;

import by.kovaliov.notificationsenderservice.model.SendNotification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SendNotificationDto {
  private SendNotification sendNotification;
  private NotificationDto notificationDto;
}
