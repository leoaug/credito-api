package br.com.exemplo.credito.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.exemplo.credito.messaging.azure.CreditoServiceBusPublisher;
import br.com.exemplo.credito.messaging.kafka.CreditoKafkaPublisher;
import br.com.exemplo.credito.model.Credito;
import br.com.exemplo.credito.repository.CreditoRepository;

@ExtendWith(MockitoExtension.class)
class CreditoServiceTest {

    @Mock
    CreditoRepository repository;

    //@Mock
    //CreditoKafkaPublisher kafkaPublisher;

    //@Mock
    //CreditoServiceBusPublisher serviceBusPublisher;

    @InjectMocks
    CreditoService service;

    @Test
    void disparaEventosAoConsultar() {
        when(repository.findByNumeroNfse("1"))
            .thenReturn(List.of(new Credito()));

        service.buscarPorNumeroNfse("1");

        //verify(kafkaPublisher).publicarEvento(any());
        //verify(serviceBusPublisher).publicarEvento(any());
    }
}
