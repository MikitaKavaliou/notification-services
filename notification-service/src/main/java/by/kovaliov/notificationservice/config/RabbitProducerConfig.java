package by.kovaliov.notificationservice.config;

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
  private String notificationResponseQueueName;
  @Value("${spring.rabbitmq.queue.producer.exchange}")
  private String notificationResponseFanoutExchangeName;


  @Bean
  public Queue queueToProduce() {
    return new Queue(notificationResponseQueueName, true);
  }

  @Bean
  public FanoutExchange exchangeToProduce() {
    return new FanoutExchange(notificationResponseFanoutExchangeName);
  }

  @Bean
  public Binding producersBinding(@Qualifier("queueToProduce") Queue queueToProduce,
                                  @Qualifier("exchangeToProduce")FanoutExchange exchangeToProduce) {
    return BindingBuilder.bind(queueToProduce).to(exchangeToProduce);
  }
}