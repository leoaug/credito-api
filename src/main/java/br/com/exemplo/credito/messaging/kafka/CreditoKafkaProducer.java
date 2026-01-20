package br.com.exemplo.credito.messaging.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreditoKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void enviarEvento(String mensagem) {
        kafkaTemplate.send("credito-topic", mensagem);
    }
}
