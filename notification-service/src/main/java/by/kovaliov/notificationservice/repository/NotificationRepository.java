package by.kovaliov.notificationservice.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import by.kovaliov.notificationservice.model.Notification;


public interface NotificationRepository extends MongoRepository<Notification, Long> {

  Notification findFirstByUserIdOrderByIdAsc(long userId);
}
