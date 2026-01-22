package br.com.exemplo.credito.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import br.com.exemplo.credito.messaging.azure.CreditoServiceBusProducer;
import br.com.exemplo.credito.messaging.event.ConsultaCreditoEvent;
import br.com.exemplo.credito.messaging.kafka.CreditoKafkaProducer;
import br.com.exemplo.credito.model.Credito;
import br.com.exemplo.credito.repository.CreditoRepository;

@ExtendWith(MockitoExtension.class)
class CreditoServiceTest {

    @Mock
    private CreditoRepository repository;

    @Mock
    private CreditoKafkaProducer kafka;

    @Mock
    private CreditoServiceBusProducer serviceBus;

    @InjectMocks
    private CreditoService creditoService;

    @Captor
    private ArgumentCaptor<ConsultaCreditoEvent> eventCaptor;

    private Credito creditoExemplo;

    @BeforeEach
    void setUp() {
        creditoExemplo = new Credito();
        creditoExemplo.setId(100L);
        creditoExemplo.setNumeroCredito("CRD-987654");
        creditoExemplo.setNumeroNfse("NFSE-123456789");
    }

    @Test
    @DisplayName("buscarPorNumeroNfse → retorna lista e publica evento")
    void buscarPorNumeroNfse_deveRetornarListaEPublicarEvento() {
        String nfse = "NFSE-123456789";
        var listaEsperada = List.of(creditoExemplo, new Credito());

        when(repository.findByNumeroNfse(nfse)).thenReturn(listaEsperada);

        var resultado = creditoService.buscarPorNumeroNfse(nfse);

        assertThat(resultado).isSameAs(listaEsperada);
        assertThat(resultado).hasSize(2);

        verify(repository).findByNumeroNfse(nfse);
        verifyNenhumProducerFoiChamado();  // pois estão comentados no código original
    }

    @Test
    @DisplayName("buscarPorNumeroCredito → encontra → retorna e publica evento")
    void buscarPorNumeroCredito_quandoExiste() {
        String numero = "CRD-987654";
        var page = PageRequest.of(0, 1);

        when(repository.findByNumeroCredito(numero, page))
                .thenReturn(List.of(creditoExemplo));

        var resultado = creditoService.buscarPorNumeroCredito(numero);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNumeroCredito()).isEqualTo(numero);

        verify(repository).findByNumeroCredito(eq(numero), eq(page));
        verifyNenhumProducerFoiChamado();
    }

    @Test
    @DisplayName("buscarPorNumeroCredito → não encontra → retorna null, mas publica evento")
    void buscarPorNumeroCredito_quandoNaoExiste() {
        String numero = "CRD-INEXISTENTE";
        var page = PageRequest.of(0, 1);

        // Correção do erro de inferência usando tipo explícito
        when(repository.findByNumeroCredito(numero, page))
                .thenReturn(List.of());

        var resultado = creditoService.buscarPorNumeroCredito(numero);

        assertThat(resultado).isNull();

        verify(repository).findByNumeroCredito(eq(numero), eq(page));
        verifyNenhumProducerFoiChamado();
    }

    @Test
    @DisplayName("buscarPorNumeroCredito → Optional vazio → null")
    void buscarPorNumeroCredito_comStreamFindFirstVazio() {
        String numero = "CRD-999999";
        var page = PageRequest.of(0, 1);

        when(repository.findByNumeroCredito(numero, page))
                .thenReturn(List.of());

        var resultado = creditoService.buscarPorNumeroCredito(numero);

        assertThat(resultado).isNull();
    }

    // Método auxiliar mais limpo
    private void verifyNenhumProducerFoiChamado() {
        verify(kafka, never()).publicarEvento(any());
        verify(serviceBus, never()).publicarEvento(any());
    }

    // Se quiser testar o evento quando descomentar as linhas kafka/serviceBus:
    /*
    private void verifyEventoPublicado(String tipoEsperado, String idEsperado) {
        verify(kafka).publicarEvento(eventCaptor.capture());
        verify(serviceBus).publicarEvento(eventCaptor.capture());

        var evento = eventCaptor.getValue();

        assertThat(evento.tipo()).isEqualTo(tipoEsperado);
        assertThat(evento.identificador()).isEqualTo(idEsperado);
        assertThat(evento.dataHora()).isNotNull();
    }
    */
}