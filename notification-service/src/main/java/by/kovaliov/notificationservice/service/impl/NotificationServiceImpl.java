package by.kovaliov.notificationservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.kovaliov.notificationservice.model.Notification;
import by.kovaliov.notificationservice.repository.NotificationRepository;
import by.kovaliov.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

  private final NotificationRepository notificationRepository;

  @Override
  public List<Notification> findAll() {
    return notificationRepository.findAll();
  }

  @Override
  public Notification save(Notification notification) {
    return notificationRepository.save(notification);
  }

  @Override
  @Transactional
  public Notification pollNotificationByUserId(long userId) {
    Notification notification = notificationRepository.findFirstByUserIdOrderByIdAsc(userId);
    notificationRepository.delete(notification);
    return notification;
  }
}
