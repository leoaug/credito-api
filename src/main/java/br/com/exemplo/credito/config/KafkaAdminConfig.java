package br.com.exemplo.credito.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import br.com.exemplo.credito.constants.CreditoConstants;

//@Configuration
public class KafkaAdminConfig {

	
	@Bean
	public NewTopic consultaCreditoTopic() {
		return TopicBuilder.name(CreditoConstants.KAFKA_TOPIC).
							partitions(1).
							replicas(1).
							build();
	}
	
}
