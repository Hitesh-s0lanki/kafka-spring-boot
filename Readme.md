# Kafka + Spring Cloud Ecosystem

This project demonstrates how to integrate **Apache Kafka** with **Spring Cloud Stream** and **Spring Cloud Function**, including Docker-based setup and Postman testing.

## 1. Kafka Basics

- **Topic**: Logical stream of messages.
- **Partition**: Unit of parallelism within a topic.
- **Broker**: Kafka server that holds topic partitions.
- **Zookeeper**: Coordinates distributed systems (used in older Kafka versions, optional with newer versions).

> Example:  
> A topic `orders` may have 3 partitions split across 2 brokers.

---

## 2. Kafka Docker Commands

Run Kafka locally with Docker:

```bash
# Start Kafka + Zookeeper (using Bitnami)
docker-compose up -d

# View running containers
docker ps

# Access Kafka shell
docker exec -it kafka /bin/bash

# Create a topic
kafka-topics.sh --create --topic my-topic-new --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

# List topics
kafka-topics.sh --list --bootstrap-server localhost:9092
```

---

## 3. Serializer & Deserializer

**Producer**:

- `key-serializer`: `StringSerializer`
- `value-serializer`: `JsonSerializer`

**Consumer**:

- `key-deserializer`: `StringDeserializer`
- `value-deserializer`: `ErrorHandlingDeserializer`
  with delegate set to `JsonDeserializer`

> JSON Serialization ensures proper object mapping for domain models like `RiderLocation`.

---

## 4. Spring Cloud Function

Modular logic via `@Bean` functions:

```java
@Bean
public Function<String, String> uppercase() {
    return value -> value.toUpperCase();
}
```

Invoke via HTTP:

```http
POST /uppercase
Content-Type: text/plain

hello kafka
```

Response:

```
HELLO KAFKA
```

---

## 5. Spring Cloud Stream

Binding functions to Kafka topics:

```yaml
spring:
  cloud:
    function:
      definition: sendRiderLocation
    stream:
      bindings:
        sendRiderLocation-out-0:
          destination: my-topic-new
          content-type: application/json
```

> Allows mapping a function output directly to a Kafka topic.

---

## 6. Postman Documentation

**Test Kafka Producer via HTTP**:

- Endpoint: `POST /sendRiderLocation`
- Body:

```json
{
  "riderId": "123",
  "latitude": 19.123,
  "longitude": 72.456
}
```

> This sends data to `my-topic-new` and can be consumed by the consumer service.

---

### Folder Structure

```
KAFKA/
│
├── consumer/              # Kafka consumer microservice
├── producer/              # Kafka producer + cloud function
├── images/                # Postman or architecture images
├── command.md             # CLI commands and helper notes
└── Readme.md              # This file
```

---

### 🚀 To Run

1. Start Kafka using Docker.
2. Run producer and consumer services.
3. Test using Postman or curl.

---

### 🛠 Tech Stack

- Java + Spring Boot
- Spring Cloud Stream / Function
- Apache Kafka
- Docker Compose
- Postman (for API tests)

---
