package by.kovaliov.notificationsenderservice.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import by.kovaliov.notificationsenderservice.model.SendNotification;
import by.kovaliov.notificationsenderservice.model.SendNotificationStatus;
import by.kovaliov.notificationsenderservice.repository.SendNotificationRepository;
import by.kovaliov.notificationsenderservice.service.QueueProducerService;
import by.kovaliov.notificationsenderservice.service.SendNotificationService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SendNotificationServiceImpl implements SendNotificationService {

  private final SendNotificationRepository sendNotificationRepository;
  private final QueueProducerService queueProducerService;

  @Override
  public SendNotification create(SendNotification sendNotification) {
    sendNotification.setSendStatus(SendNotificationStatus.IN_PROGRESS);
    queueProducerService.produce(sendNotification.getUserId());
    return sendNotificationRepository.save(sendNotification);
  }

  @Override
  public void update(SendNotification sendNotification) {
    sendNotificationRepository.save(sendNotification);
  }

  @Override
  public List<SendNotification> findAll() {
    return sendNotificationRepository.findAll();
  }

  @Override
  public SendNotification findById(long id) {
    return sendNotificationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
  }

  @Override
  public SendNotification findByUserId(long userId) {
    return sendNotificationRepository.findFirstByUserIdAndSendStatusOrderByIdAsc(userId, SendNotificationStatus.IN_PROGRESS);
  }
}
