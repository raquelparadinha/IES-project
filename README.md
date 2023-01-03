# IES-project

## Prison Monitorization System - LockedIn

| NMec   | Email                 | Name             | Role          |
| ------ | --------------------- | ---------------- | ------------- |
| 102491 | raquelparadinha@ua.pt | Raquel Paradinha | Team Manager  |
| 103234 | paulojnpinto02@ua.pt  | Paulo Pinto      | Product Owner |
| 103341 | miguelamatos@ua.pt    | Miguel Matos     | Architect     |
| 104142 | tiagogcarvalho@ua.pt  | Tiago Carvalho   | DevOps Master |

## Remote

The application can be accessed at 192.168.160.235:3000.
Only available inside UA's network.

### Containers and ports

- Frontend (React Client)
  - Port: 3000
  - Credentials:
    - Warden: pareidreds@aettua.pt, passwd: soulinda
    - Guard: mariosantos@lockedin.com, passwd: estouro
- Backend (Spring API)
  - Port: 5001
- Datagen (Python Script)
- Message Broker (RabbitMQ)
  - HTTP Port: 15672
  - AMQP Port: 5672
- Database (MongoDB)
  - Port: 27017
