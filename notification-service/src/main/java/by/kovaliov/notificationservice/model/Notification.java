package by.kovaliov.notificationservice.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "notifications")
public class Notification implements Serializable {
  @Id
  private long id;
  @Indexed
  private long userId;
  private String topic;
  private String content;
}
