package by.kovaliov.notificationsenderservice.dto;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class NotificationDto {
  @Id
  private long id;
  private long userId;
  private String topic;
  private String content;
}
