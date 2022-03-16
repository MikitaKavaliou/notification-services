package by.kovaliov.notificationsenderservice.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class SendNotification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @NotNull
  private long userId;
  @NotNull
  private String address;
  @Enumerated(EnumType.STRING)
  @NotNull
  private SendNotificationType sendType;
  @Enumerated(EnumType.STRING)
  private SendNotificationStatus sendStatus;
}
