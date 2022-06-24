package com.nttdata.bootcamp.yankidomain.config;

import com.nttdata.bootcamp.yankidomain.model.Payment;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

/**
 * KafkaConsumerConfig.
 */
@Configuration
public class KafkaConsumerConfig {

  @Value("${spring.kafka.consumer.bootstrap-servers}")
  private String bootstrapServerConfig;

  /**
   * consumerFactory.
   */
  @Bean
  public ConsumerFactory<String, Payment> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServerConfig);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
        new JsonDeserializer<>(Payment.class));
  }

  /**
   * kafkaListenerContainerFactory.
   */
  @Bean
  KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Payment>> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Payment> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}
