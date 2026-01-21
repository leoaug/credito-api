package br.com.exemplo.credito.messaging.azure;

import org.springframework.stereotype.Component;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.exemplo.credito.messaging.event.ConsultaCreditoEvent;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreditoServiceBusPublisher {

    //private final ServiceBusSenderClient senderClient;
    private final ObjectMapper mapper = new ObjectMapper();

    /*
    public void publicarEvento(ConsultaCreditoEvent event) {
        try {
            String payload = mapper.writeValueAsString(event);
            senderClient.sendMessage(new ServiceBusMessage(payload));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar evento para Azure Service Bus", e);
        }
    }
    */
}
