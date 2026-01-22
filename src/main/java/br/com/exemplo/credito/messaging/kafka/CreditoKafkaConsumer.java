package br.com.exemplo.credito.messaging.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.exemplo.credito.constants.CreditoConstants;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CreditoKafkaConsumer {
	/*
	@KafkaListener(topics = CreditoConstants.KAFKA_TOPIC , groupId = CreditoConstants.KAFKA_GROUP_ID)
    public void consumir(String mensagem) {
        log.info("Mensagem recebida: {}", mensagem);
    }
    */
}
