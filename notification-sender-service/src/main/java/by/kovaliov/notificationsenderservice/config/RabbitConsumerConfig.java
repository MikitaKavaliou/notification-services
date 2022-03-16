package by.kovaliov.notificationsenderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import by.kovaliov.notificationsenderservice.service.QueueConsumerService;

@Configuration
public class RabbitConsumerConfig {

  @Value("${spring.rabbitmq.queue.consumer.name}")
  private String notificationResponseQueueName;

  @Value("${spring.rabbitmq.queue.consumer.exchange}")
  private String notificationResponseFanoutExchangeName;

  private static final String LISTENER_METHOD = "receiveMessage";

  @Bean
  public Queue queueToConsume() {
    return new Queue(notificationResponseQueueName, true);
  }

  @Bean
  public FanoutExchange exchangeToConsume() {
    return new FanoutExchange(notificationResponseFanoutExchangeName);
  }

  @Bean
  public Binding producersBinding(@Qualifier("queueToConsume") Queue queueToConsume,
                                  @Qualifier("exchangeToConsume") FanoutExchange exchangeToConsume) {
    return BindingBuilder.bind(queueToConsume).to(exchangeToConsume);
  }

  @Bean
  public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                                  MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(notificationResponseQueueName);
    container.setMessageListener(listenerAdapter);
    return container;
  }

  @Bean
  MessageListenerAdapter listenerAdapter(QueueConsumerService consumer) {
    return new MessageListenerAdapter(consumer, LISTENER_METHOD);
  }
}
