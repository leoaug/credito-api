# credito-api
API de API DE CONSULTA DE CRÉDITOS - KAFKA AZURE

 
1 - Instalar Docker (se não tiver):

	sudo apt update
	sudo apt install -y docker.io docker-compose
	sudo usermod -aG docker $USER
	newgrp docker

2 - Subir o Kafka:

	docker compose -f /caminhoCompletoDaAplicacao/docker/docker-compose.yml up -d

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
		
6 - application.yml setar as configuracoes do BD (username, password e url)

	  datasource:
	    url: jdbc:postgresql://localhost:5432/seuDataBase
	    username: seuUserName
	    password: suaSenha
	    driver-class-name: org.postgresql.Driver
	
