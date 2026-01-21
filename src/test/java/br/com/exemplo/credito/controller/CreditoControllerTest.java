package br.com.exemplo.credito.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.exemplo.credito.model.Credito;
import br.com.exemplo.credito.service.CreditoService;

@WebMvcTest(CreditoController.class)
class CreditoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CreditoService service;

    @Test
    void deveConsultarPorNfse() throws Exception {
        when(service.buscarPorNumeroNfse("123"))
                .thenReturn(List.of(new Credito()));

        mockMvc.perform(get("/api/creditos/123"))
                .andExpect(status().isOk());
    }
}
