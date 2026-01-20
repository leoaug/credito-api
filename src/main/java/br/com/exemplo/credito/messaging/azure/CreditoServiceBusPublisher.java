package br.com.exemplo.credito.messaging.azure;

import br.com.exemplo.credito.messaging.event.ConsultaCreditoEvent;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class CreditoServiceBusPublisher {

    private final ServiceBusSenderClient senderClient;
    private final ObjectMapper mapper = new ObjectMapper();

    public CreditoServiceBusPublisher(ServiceBusSenderClient senderClient) {
        this.senderClient = senderClient;
    }

    public void publicarEvento(ConsultaCreditoEvent event) {
        try {
            String payload = mapper.writeValueAsString(event);
            senderClient.sendMessage(new ServiceBusMessage(payload));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar evento para Azure Service Bus", e);
        }
    }
}
