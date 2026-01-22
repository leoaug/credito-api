# credito-api
API de API DE CONSULTA DE CRÉDITOS - KAFKA AZURE

Pré Requisitos:
- Java 21 e Spring Boot 3.5.4
- Criar um schema do banco de dados Postgres definido na classe 'CreditoConstants' - no campo public static final String SCHEMA = "credito-schema";
- Caso queira testar o azure (já com a conta criada) e o kafta , atentar para os seguintes arquivos:
  	1. env.properties as variáveis :
  	   1.1 - AZURE_SERVICEBUS_CONNECTION_STRING=Endpoint=sb://suaNameSpace.servicebus.windows.net/
			 AZURE_SERVICEBUS_QUEUE_NAME=credito-queue
    2. na classe 'AzureServiceBusConfig' descomentar o método 'serviceBusSenderClient()' para fazer a conexão no serviço;
    3. na classe 'CreditoServiceBusPublisher' descomentar o '@Component' o '@RequiredArgsConstructor';   
    4. na classe 'CreditoService' descomentar a linha 45 'serviceBus.publicarEvento(event)' caso queira desativar o kafka comentar a linha 44 'kafka.publicarEvento(event);'
    5. na classe 'KafkaAdminConfig' se quiser desabilitar comente o @Configuration e na classe 'CreditoKafkaConsumer' comente o metodo 'consumir(String mensagem)'
       
 
 
1 - Instalar Docker (se não tiver):

	sudo apt update
	sudo apt install -y docker.io docker-compose
	sudo usermod -aG docker $USER
	newgrp docker

2 - Subir o Kafka:

	docker compose -f /caminhoCompletoDaAplicacao/docker/docker-compose.yml up -d
	ou
	docker compose up -d (se tiver na pasta docker-compose.yml)


3 - Pegue o nome do servico pelo comando:

	docker ps --format "table {{.Names}}\t{{.Status}}"


4 - Usar o Azure:

	Como usar Azure Service Bus corretamente
		🧭 Passo 1 — Criar no Portal Azure

			Portal: https://portal.azure.com
	
			Criar recurso → Service Bus (Barramento de Serviço)
	
			Tipo: Standard ou Premium
	
			Queue (ex: credito-queue)

	    🧭Passo 2 — Obter Connection String

			Azure Portal →
			Service Bus → Shared access policies → RootManageSharedAccessKey	
	
			Endpoint=sb://xxxx.servicebus.windows.net/;
			SharedAccessKeyName=RootManageSharedAccessKey;
			SharedAccessKey=XXXX

5 - Setar o env.properties da aplicação as seguintes variáveis:

		AZURE_SERVICEBUS_CONNECTION_STRING=Endpoint=sb://suaNameSpace.servicebus.windows.net/
		AZURE_SERVICEBUS_QUEUE_NAME=credito-queue
		
6 - application.yml setar as configuracoes do BD (username, password e url) atualizar no docker-compose.yml também

	  datasource:
	    url: jdbc:postgresql://localhost:5432/seuDataBase
	    username: seuUserName
	    password: suaSenha
	    driver-class-name: org.postgresql.Driver

	
