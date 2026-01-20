Requisitos:

Ter o habbit mq server instalado na máquina

depois configurar a URL do WebHook com os passos abaixo:
acesse :
	https://webhook-test.com/
gere e copie a url e em seguida coloque no application.properties abaixo:

	webhook.url='sua URL gerada'

Ex da url gerada: webhook.url=https://webhook-test.com/0bf3ea6cd405ca9350e85d3be48e71b8

pra mudar o tempo da fila te que deletar antes no servidor com o seguinte comando:
	rabbitmqctl delete_queue github.delay.queue
