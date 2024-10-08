### API de avaliação de crédito

Aplicação modular baseada em microserviços com utilização de rabbitmq para mensageria e keycloak para gerenciamento de identidades.

Para testar a api deverá ter instalado docker.

Primeiro execute:
```
docker run -p 8081:8080 -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:26.0.0 start-dev
```
Após executar acesse via `http://localhost:8081`

Para logar utilize `usuario: admin senha: admin`

Na pagina inicial crie um realm, defina um nome em `realm name` e crie
agora selecione o realm que voce acabou de criar va ate `clients`.

Clique em `create clients` defina um `client id` e clique em next. 

Marque as opções `Client authentication`, `Authorization`, `Service accounts roles`, `OAuth 2.0 Device Authorization Grant` clique em next e save.

Agora Clique me `client scopes` e depois em `create clients scope` defina um `name` e em `type` defina `default` e save.

Agora vá em `clients` clique no client que voce criou na tela a seguir clique em `credentials` e copie seu `client secret`.

Depois clique em `realm settings` desça a tela e clique em `OpenID endpoint configuration` e copie a url em `authorization_endpoint` 

Agora com o `client id` e `client secret` e o `scope` que voce criou vá até o postman.

Abra uma nova request e clique `authorization` selecione o tipo `oauth 2.0`
e preencha com os dados informados anteriormente.

Agora vá no rabbimq.

```
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4.0-management
```
Acesse: `http://localhost:15672/#/queues`.

Crie uma fila em `queues and streams` e defina um nome em `name` e clique em `add queue`.

Agora clone o projeto

```
git clone https://github.com/herlandio/microservicos-mensageria-springboot.git
```

Acesse a pasta `modular-herlandio7` e execute `mvn clean package -DskipTests`  

Depois inicie os modulos.


