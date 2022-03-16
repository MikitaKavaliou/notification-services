package by.kovaliov.notificationsenderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.kovaliov.notificationsenderservice.model.SendNotification;
import by.kovaliov.notificationsenderservice.model.SendNotificationStatus;

@Repository
public interface SendNotificationRepository extends JpaRepository<SendNotification, Long> {

  SendNotification findFirstByUserIdAndSendStatusOrderByIdAsc(long userId, SendNotificationStatus sendNotificationStatus);
}
