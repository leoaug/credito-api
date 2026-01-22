package br.com.exemplo.credito.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class CreditoCorsConfigTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    // Método auxiliar para inicializar o MockMvc com os filtros e configurações reais
    private MockMvc getMockMvc() {
        if (mockMvc == null) {
            mockMvc = MockMvcBuilders
                    .webAppContextSetup(webApplicationContext)
                    .build();
        }
        return mockMvc;
    }

    @Test
    @DisplayName("Deve permitir requisição OPTIONS com origem localhost:4200")
    void devePermitirCorsParaOrigemCorreta() throws Exception {
        getMockMvc().perform(
                options("/api/clientes")
                        .header("Origin", "http://localhost:4200")
                        .header("Access-Control-Request-Method", "POST")
                        .header("Access-Control-Request-Headers", "Content-Type, Authorization")
        )
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:4200"))
                .andExpect(header().string("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS"));
    }

   

    @Test
    @DisplayName("Deve aceitar cabeçalhos arbitrários quando origem é permitida")
    void deveAceitarQualquerHeaderQuandoOrigemValida() throws Exception {
        getMockMvc().perform(
                options("/api/usuarios")
                        .header("Origin", "http://localhost:4200")
                        .header("Access-Control-Request-Headers", "X-Custom-Header, Authorization, Meu-Header")
                        .header("Access-Control-Request-Method", "PUT")
        )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve permitir credenciais (cookies, auth headers) quando origem é permitida")
    void devePermitirCredentialsTrue() throws Exception {
        getMockMvc().perform(
                options("/api/auth/login")
                        .header("Origin", "http://localhost:4200")
                        .header("Access-Control-Request-Method", "POST")
        )
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Credentials", "true"));
    }

    @Test
    @DisplayName("Max-Age deve estar configurado em 3600 segundos")
    void deveTerMaxAgeConfigurado() throws Exception {
        getMockMvc().perform(
                options("/api/teste")
                        .header("Origin", "http://localhost:4200")
                        .header("Access-Control-Request-Method", "DELETE")
        )
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Max-Age", "3600"));
    }
}