package br.com.exemplo.credito.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

@Configuration
public class AzureServiceBusConfig {

    @Value("${spring.azure.servicebus.connection-string}")
    private String connectionString;

    @Value("${spring.azure.servicebus.queue-name}")
    private String queueName;

    /* descomentar quando tiver a conta azure service bus
    @Bean
    public ServiceBusSenderClient serviceBusSenderClient() {
        return new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient();
    }
    */
}
