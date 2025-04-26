#Medilabo

## Green Code

- Merge lightweight microservices into a modular monolith: this would drastically reduce CPU/RAM usage and simplify deployment.
- Adopt constructor injection for all Spring services.
- Add lightweight logs (INFO level) to understand application flows without overloading memory.
- Use record classes for simple objects (such as Patient, Note), to improve memory efficiency.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine with docker for development purposes.

### Prerequisites

- Git
- Java
- Maven
- Docker
- Mysql
- MongoDB

### Installation

The following commands will be done in a terminal

1. Clone the repository on your machine:
   ```bash
   git clone https://github.com/wysgar/P9MediLabo.git

3. Compilation
   Run the following command in each microservices to build their .jar
   ```bash
   mvn -DskipTests=true package

4. Docker compose
   Run the following command in the project root to build the docker images and launch the containers
   ```bash
   docker-compose up --build

5. Access the application
   [Site]([URL](http://localhost:8080/patient/list))
   Credentials :
     - Username : user / Password : password
     - Username : admin / Password : password
