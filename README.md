# SOA Demo: NovaMart

NovaMart is a lightweight e-commerce demo showcasing a Spring Boot microservices backend, React micro-frontends, and an event-driven architecture.
The system is containerized and designed to run in Docker, while MySQL remains local on the host.

## Architecture Highlights
- **API Gateway** exposes secured REST endpoints (JWT) and aggregates catalog/order data.
- **Microservices**: catalog-service, order-service, notification-service.
- **Messaging**: RabbitMQ for command messages and Kafka for event streaming.
- **FaaS**: LocalStack Lambda for order recommendations.
- **WebSockets**: notification-service pushes order events to the UI.
- **Micro-frontends**: shell + catalog + orders MFEs.
- **Load balancing**: Nginx in front of the gateway (scale gateway containers with Docker Compose).

## Running locally
1. Start MySQL on your host and create a database named `novamart`.
2. Export the host IP for containers to reach MySQL:
   - macOS/Windows:
     ```bash
     export MYSQL_HOST=host.docker.internal
     ```
   - Linux (replace with your host IP on the Docker bridge, often `172.17.0.1`):
     ```bash
     export MYSQL_HOST=172.17.0.1
     ```
3. Build and start the stack:

```bash
docker compose up --build
```

4. Open `http://localhost:8080`.
5. Authenticate via the API or the UI using:
   - username: `demo`
   - password: `demo123`

## Services and ports
- Nginx: 8080
- API Gateway: 8081
- Catalog: 8082
- Orders: 8083
- Notifications (WebSocket): 8084
- RabbitMQ: 5672 (15672 management)
- Kafka: 9092
- LocalStack (Lambda): 4566

## Documentation
See `docs/uml` and `docs/c4` for diagrams and models.

## Troubleshooting Docker builds
- If Docker reports `input/output error` while building, try cleaning BuildKit cache:
  ```bash
  docker builder prune -f
  ```
- If Maven fails with `ClassFormatError`, clear cached layers and rebuild:
  ```bash
  docker compose build --no-cache
  ```
