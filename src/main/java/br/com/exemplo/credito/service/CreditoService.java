package br.com.exemplo.credito.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.exemplo.credito.messaging.azure.CreditoServiceBusPublisher;
import br.com.exemplo.credito.messaging.event.ConsultaCreditoEvent;
import br.com.exemplo.credito.messaging.kafka.CreditoKafkaPublisher;
import br.com.exemplo.credito.model.Credito;
import br.com.exemplo.credito.repository.CreditoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditoService {

    private final CreditoRepository repository;
    
    private final CreditoKafkaPublisher kafka;
    
    //private final CreditoServiceBusPublisher serviceBus;
   
    public List<Credito> buscarPorNumeroNfse(String nfse) {
        var result = repository.findByNumeroNfse(nfse);
        publicarEvento("CONSULTA_NFSE", nfse);
        return result;
    }

    public Credito buscarPorNumeroCredito(String numero) {
        var credito = repository.findByNumeroCredito(numero)
            .orElseThrow(() -> new RuntimeException("Crédito não encontrado"));
        publicarEvento("CONSULTA_CREDITO", numero);
        return credito;
    }

    private void publicarEvento(String tipo, String id) {
        var event = new ConsultaCreditoEvent(tipo, id, LocalDateTime.now());
        //kafka.publicarEvento(event);
        //serviceBus.publicarEvento(event);
    }
}
