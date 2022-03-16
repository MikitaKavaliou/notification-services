package by.kovaliov.notificationsenderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitProducerConfig {

  @Value("${spring.rabbitmq.queue.producer.name}")
  private String notificationRequestQueueName;

  @Value("${spring.rabbitmq.queue.producer.exchange}")
  private String notificationRequestFanoutExchangeName;

  @Bean
  public Queue queueToProduce() {
    return new Queue(notificationRequestQueueName, true);
  }

  @Bean
  public FanoutExchange exchangeToProduce() {
    return new FanoutExchange(notificationRequestFanoutExchangeName);
  }

  @Bean
  public Binding producerBinding(@Qualifier("queueToProduce") Queue queueToConsume,
                                 @Qualifier("exchangeToProduce") FanoutExchange exchangeToConsume) {
    return BindingBuilder.bind(queueToProduce()).to(exchangeToProduce());
  }
}