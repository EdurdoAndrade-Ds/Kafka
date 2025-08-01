# Kafka Java Example 🧩

Este repositório contém um exemplo prático de integração entre **Apache Kafka** e **Spring Boot**, com um produtor enviando mensagens e dois consumidores distintos escutando partições diferentes de um mesmo tópico.

---

## 📦 Estrutura do Projeto

```
Kafka/
├── producer/         # Aplicação produtora (envia pedidos)
├── consumer01/       # Consumidor da partição 1
├── consumer02/       # Consumidor da partição 0
```

---

## 🚀 Tecnologias

- Java 17
- Spring Boot
- Spring Kafka
- Apache Kafka
- Docker

---

## 🐳 Como subir o Apache Kafka com Docker

### 1. Baixe a imagem oficial do Apache Kafka

```bash
docker pull apache/kafka:4.0.0
```

### 2. Suba o container com o Kafka na porta padrão

```bash
docker run -p 9092:9092 apache/kafka:4.0.0
```

> Isso roda o Kafka em modo KRaft (sem Zookeeper) na porta `9092`, que será usada pelos serviços Spring Boot.

---

## ☕ Como rodar os serviços da aplicação

### 1. Clone este repositório

```bash
git clone https://github.com/EdurdoAndrade-Ds/Kafka.git
cd Kafka
```

### 2. Inicie os consumidores

Abra dois terminais ou abas:

#### No terminal 1:

```bash
cd consumer01
./mvnw spring-boot:run
```

#### No terminal 2:

```bash
cd consumer02
./mvnw spring-boot:run
```

### 3. Inicie o produtor

Em um terceiro terminal:

```bash
cd producer
./mvnw spring-boot:run
```

> Isso iniciará a API REST do produtor em `http://localhost:8081`.

---

## 📬 Como testar com Postman ou curl

Envie uma requisição POST para o endpoint:

```
POST http://localhost:8081/orders
```

Com o seguinte JSON no corpo:

```json
{
  "name": "Usuario",
  "description": "Compra1",
  "amount": 150.00
}
```

Você verá nos logs dos consumidores que a mensagem foi recebida por **consumer01** ou **consumer02**, dependendo da partição.

---

## ⚙️ Como funciona

- O **producer** envia mensagens para o tópico `order-processed` (com 2 partições).
- O **consumer02** está configurado para consumir **apenas a partição 0**.
- O **consumer01** está configurado para consumir **apenas a partição 1**.
- A partição de destino é definida no envio da mensagem, permitindo distribuição balanceada.

---

## 🧠 O que você aprende com esse projeto

- Como configurar Kafka com Spring Boot.
- Criar tópicos automaticamente com múltiplas partições via `KafkaAdmin`.
- Controlar quais partições cada `@KafkaListener` consome.
- Tratar erros de envio para partições inexistentes (`TimeoutException`).
- Enviar mensagens via KafkaTemplate e consumir em múltiplos microserviços.

---

## 👨‍💻 Autor

Desenvolvido por [Eduardo Andrade](https://github.com/EdurdoAndrade-Ds)

---

## 📜 Licença

MIT License © 2025

