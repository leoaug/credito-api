package br.com.exemplo.credito.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    	Optional<Credito> credito =
    		    repository.findByNumeroCredito(numero, PageRequest.of(0, 1))
    		              .stream()
    		              .findFirst();

        publicarEvento("CONSULTA_CREDITO", numero);
        return credito.isEmpty() ? null : credito.get(); 
    }

    private void publicarEvento(String tipo, String id) {
        var event = new ConsultaCreditoEvent(tipo, id, LocalDateTime.now());
        //kafka.publicarEvento(event);
        //serviceBus.publicarEvento(event);
    }
}
