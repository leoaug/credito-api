package br.com.exemplo.credito.messaging.event;

import java.time.LocalDateTime;

public record ConsultaCreditoEvent(String tipoConsulta, String identificador, LocalDateTime dataHora) {
}
