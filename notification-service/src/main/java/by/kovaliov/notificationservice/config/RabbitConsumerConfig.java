package by.kovaliov.notificationservice.config;

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

import by.kovaliov.notificationservice.service.QueueConsumerService;

@Configuration
public class RabbitConsumerConfig {

  @Value("${spring.rabbitmq.queue.consumer.name}")
  private String notificationRequestQueueName;
  @Value("${spring.rabbitmq.queue.consumer.exchange}")
  private String notificationRequestFanoutExchangeName;

  private static final String LISTENER_METHOD = "receiveMessage";

  @Bean
  public Queue queueToConsume() {
    return new Queue(notificationRequestQueueName, true);
  }

  @Bean
  public FanoutExchange exchangeToConsume() {
    return new FanoutExchange(notificationRequestFanoutExchangeName);
  }

  @Bean
  public Binding consumersBinding(@Qualifier("queueToConsume") Queue queueToConsume,
                                  @Qualifier("exchangeToConsume")FanoutExchange exchangeToConsume) {
    return BindingBuilder.bind(queueToConsume).to(exchangeToConsume);
  }

  @Bean
  public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                                  MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(notificationRequestQueueName);
    container.setMessageListener(listenerAdapter);
    return container;
  }

  @Bean
  MessageListenerAdapter listenerAdapter(QueueConsumerService consumer) {
    return new MessageListenerAdapter(consumer, LISTENER_METHOD);
  }
}
