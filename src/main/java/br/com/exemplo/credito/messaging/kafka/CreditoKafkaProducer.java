package br.com.exemplo.credito.messaging.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.exemplo.credito.constants.CreditoConstants;
import br.com.exemplo.credito.messaging.event.ConsultaCreditoEvent;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditoKafkaProducer {

	 private final KafkaTemplate<String, ConsultaCreditoEvent> kafkaTemplate;

    public void publicarEvento(ConsultaCreditoEvent event) {
        kafkaTemplate.send(CreditoConstants.KAFKA_TOPIC, event.identificador(), event);
    }
}
