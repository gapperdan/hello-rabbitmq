# hello-rabbitmq
Sample code for using Spring Boot with RabbitMQ based on https://spring.io/guides/gs/messaging-rabbitmq/

### Download and install your local RabbitMQ server (use default settings)
https://www.rabbitmq.com/download.html

### Enable RabbitMQ Admin UI (optional)
```
./rabbitmq-plugins enable rabbitmq_management
```

### Start your local RabbitMQ server
```
./rabbitmq-server
```

### Start this app
```
./gradlew clean build bootRun
```

### Notes

#### Using the HTTP API
##### Reading a message from the queue (if message is available, of course)
*Requires BasicAuthentication (e.g. guest/guest)*

POST: localhost:15672/api/queues/%2f/spring-boot/get

Where:
- *%2f* is the vhost (encoded '/', the default vhost)
- *spring-boot* is the queue name

Request Body:
```
{"count":1,"requeue":true,"encoding":"auto"}
```

Sample response:
```
[
  {
    "payload_bytes": 11,
    "redelivered": true,
    "exchange": "",
    "routing_key": "spring-boot",
    "message_count": 0,
    "properties": {
      "delivery_mode": 1,
      "headers": {}
    },
    "payload": "Message 001",
    "payload_encoding": "string"
  }
]
```

#### Getting authentication error
```
ACCESS_REFUSED - Login was refused using authentication mechanism PLAIN. For details see the broker logfile
```
#### Solution:
- In the RabbitMQ admin UI (default: localhost:15672), create a new user and give all permissions (e.g. user=demo, password=demo)

- Create properties for your RabbitMQ instance in src/main/resources/application.properties:
```
spring.rabbitmq.username=demo
spring.rabbitmq.password=demo
```
