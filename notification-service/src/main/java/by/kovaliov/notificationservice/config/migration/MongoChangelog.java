package by.kovaliov.notificationservice.config.migration;

import java.util.ArrayList;
import java.util.List;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;

import by.kovaliov.notificationservice.model.Notification;
import by.kovaliov.notificationservice.repository.NotificationRepository;

@ChangeLog
public class MongoChangelog {

  @ChangeSet(order = "001", id = "2021_4_24_00", author = "Mikita_Kavaliou")
  public void seedDatabase(NotificationRepository notificationRepository) {
    List<Notification> notifications = new ArrayList<>();
    notifications.add(new Notification(1L,1L,"Some awesome topic","Some awesome content"));
    notifications.add(new Notification(2L,2L,"Some happy topic","Some happy content"));
    notifications.add(new Notification(3L,3L,"Some sad topic","Some sad content"));
    notificationRepository.insert(notifications);
  }
}
