package br.com.exemplo.credito.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = AzureServiceBusConfig.class)
@TestPropertySource(properties = {
        "spring.azure.servicebus.connection-string=Endpoint=sb://fake.servicebus.windows.net/;SharedAccessKeyName=FakeKey;SharedAccessKey=FakeSecret",
        "spring.azure.servicebus.queue-name=credito-queue"
})
class AzureServiceBusConfigTest {

    @Autowired
    private AzureServiceBusConfig config;

    @Test
    void testConnectionStringInjection() throws Exception {
        // Reflection para acessar o private field
        var field = AzureServiceBusConfig.class.getDeclaredField("connectionString");
        field.setAccessible(true);
        String connectionString = (String) field.get(config);
        assertEquals("Endpoint=sb://fake.servicebus.windows.net/;SharedAccessKeyName=FakeKey;SharedAccessKey=FakeSecret", connectionString);
    }

    @Test
    void testQueueNameInjection() throws Exception {
        var field = AzureServiceBusConfig.class.getDeclaredField("queueName");
        field.setAccessible(true);
        String queueName = (String) field.get(config);
        assertEquals("credito-queue", queueName);
    }
}
