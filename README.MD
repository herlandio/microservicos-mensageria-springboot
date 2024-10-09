### API de avaliação de crédito

Aplicação modular com spring boot baseada em microserviços com utilização de rabbitmq para mensageria, keycloak para gerenciamento de identidades, eureka para descoberta de serviços, docker para dockerizacão e docker compose para gerenciar containers.

Para testar a api deverá ter instalado docker.

Clone o projeto

```
git clone https://github.com/herlandio/microservicos-mensageria-springboot.git
```

Acesse a pasta `modular-herlandio7` e execute `docker-compose up -d`

Depois acesse o keycloak: `http://localhost:8081`

Para logar utilize `usuario: admin senha: admin`

Na pagina inicial crie um realm chamado herlandio7-realm, defina um nome em `realm name` e crie agora selecione o realm que voce acabou de criar vá ate `clients`.

Clique em `create clients` defina um `client id` e clique em next. 

Marque as opções `Client authentication`, `Authorization`, `Service accounts roles`, `OAuth 2.0 Device Authorization Grant` clique em next e save.

Agora Clique me `client scopes` e depois em `create clients scope` defina um `name` credit e em `type` defina `default` e save.

Agora vá em `clients` clique no client que voce criou clique em `client scopes` depois em `add client scope` no modal selecione o client scope que voce criou chamado credit no passo anterior em `add` selecione `default`

Depois clique em `realm settings` em `Frontend URL` defina `http://keycloak:8080`

Desça a tela e clique em `OpenID endpoint configuration` e copie a url em `authorization_endpoint` 

Agora vá em `clients` clique no client que voce criou clique em `credentials` e copie seu `client secret`.

Agora acesse o rabbimq `http://localhost:15672/#/queues`.

Crie uma fila em `queues and streams` e defina um nome em `name` e clique em `add queue`.

## Utilizacão:

Agora com o `client id` e `client secret` e o `scope` que voce criou vá até o postman.

Abra uma nova request e clique `authorization` selecione o tipo `oauth 2.0`
e preencha com os dados informados anteriormente.

1. Gere um token em GET `http://localhost:8081/realms/herlandio7-realm/protocol/openid-connect/token`

2. Use o token nas chamadas como bearer token

## Servicos de cartões

Criar cartão POST http://localhost:8080/cards

{
    "name": "visa Card",
    "flag":"VISA",
    "income": 5000,
    "basicLimit": 8000
}

Obter cartão pelo cpf GET http://localhost:8080/cards?cpf=0123456789

Obter cartão pelo limite GET http://localhost:8080/cards?income=5000

## Serviço de clientes

Criar cliente POST http://localhost:8080/clients

{
    "name":"teste",
    "cpf":"0123456789",
    "age":23
}

Obter Cliente via CPF GET http://localhost:8080/clients?cpf=0123456789

## Serviço de avalicação de crédito

Criar avaliação POST http://localhost:8080/credit-ratings

{
    "cpf": "0123456789",
    "income": 5000.00
}

Criar Pedido do cartão POST http://localhost:8080/credit-ratings/request-cards

{
    "id": 1,
    "cpf":"0123456789",
    "address": "test",
    "limitReleased": 18400
}

Consultar de situacão do client por CPF GET http://localhost:8080/credit-ratings/customer-situation?cpf=0123456789