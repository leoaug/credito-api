package br.com.exemplo.credito.messaging.kafka;

import br.com.exemplo.credito.messaging.event.ConsultaCreditoEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CreditoKafkaPublisher {

    private static final String TOPIC = "credito-auditoria";

    private final KafkaTemplate<String, ConsultaCreditoEvent> kafkaTemplate;

    public CreditoKafkaPublisher(KafkaTemplate<String, ConsultaCreditoEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publicarEvento(ConsultaCreditoEvent event) {
        kafkaTemplate.send(TOPIC, event.identificador(), event);
    }
}
