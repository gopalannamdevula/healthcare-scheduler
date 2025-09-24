Healthcare Scheduler (In Progress)
This repository contains a microservices-based Healthcare Scheduler application built with Spring Boot, Spring Cloud, and Docker. The project is currently in progress, and we will continue to add new features and improve the architecture in the coming days.
🚀 Current Status
•	✅ Core microservices are set up and containerized with Docker
•	✅ Service discovery with Eureka Server
•	✅ API Gateway for routing
•	✅ Databases integrated: MongoDB and PostgreSQL
•	✅ Application wiring and features are still in progress
🏗️ Existing Services
Eureka Server
•	- Acts as the service registry for all microservices.
•	- Runs on port 8761.
•	- Dashboard available at: http://localhost:8761
API Gateway
•	- Built using Spring Cloud Gateway.
•	- Handles routing to downstream services.
•	- Runs on port 8085.
Patient Service
•	- Manages patient data.
•	- Uses MongoDB as its database.
•	- Runs on port 8081.
Doctor Service
•	- Manages doctor data.
•	- Uses PostgreSQL as its database.
•	- Runs on port 8082.
Appointment Service
•	- Handles appointments between patients and doctors.
•	- Uses PostgreSQL as its database.
•	- Runs on port 8083.
🐳 Docker Setup
This project uses Docker Compose to run all services together. At the root of the project, run:
docker-compose up --build
This will start: MongoDB, PostgreSQL (doctor + appointment DBs), Eureka Server, API Gateway, Patient Service, Doctor Service, and Appointment Service.
🔮 Upcoming Features
•	- Notification Service
•	- Frontend Integration (React/Angular)
•	- Integration of AI chatbot to make decision whether doctor consultation needed or not depends on Symptoms Stated 
•	- CI/CD pipeline
 
Stay tuned! 🚀



📌 Notes
This project is under active development.

