# Medilabo

## Green Code

- Use of streams and CompletableFuture for efficient parallelism.\
  Before
  ```
  Patient patient = infoService.findById(id);
  List<Note> notes = noteService.getNoteByPatient(id);
  ```
  After
  ```
  CompletableFuture<Patient> patientFuture = CompletableFuture.supplyAsync(() ->
     infoService.findById(id)
  );
  CompletableFuture<List<Note>> notesFuture = CompletableFuture.supplyAsync(() ->
     noteService.getNoteByPatient(id)
  );
  
  CompletableFuture.allOf(patientFuture, notesFuture).join();
  
  Patient patient = patientFuture.join()
  List<Note> notes = notesFuture.join();
  ```
- Use of lightweight Docker images (Slim JDK) to reduce size and deployment time.\
  Before
  ```
  FROM openjdk:17-alpine
  ARG JAR_FILE=target/*.jar
  COPY ${JAR_FILE} app.jar
  ENTRYPOINT ["java","-jar","/app.jar"]
  ```
  After
  ```
  FROM openjdk:17-slim
  ARG JAR_FILE=target/*.jar
  COPY ${JAR_FILE} app.jar
  ENTRYPOINT ["java","-jar","/app.jar"]
  ```
- Adopt constructor injection for all Spring services.\
  Before
  ```
  @Autowired
  private final InfoService infoService;
  @Autowired
  private final NoteService noteService;
  ```
  After
  ```
  private final InfoService infoService;
  private final NoteService noteService;

  public RisqueService(InfoService infoService, NoteService noteService) {
     this.infoService = infoService;
     this.noteService = noteService;
  }
  ```
- Add lightweight logs (INFO level) to understand application flows without overloading memory.\
   Example in RisqueService:
  ```
  log.info("Beginning risk assessment for patient ID: {}", id);
  log.info("Retrieving notes for patient ID: {}", id);
  log.info("Number of triggers found: {}", triggerCount);
  log.info("Risk assessed for patient ID {}: {}", id, risque);
  ```
- Use record classes for simple objects (such as Patient, Note), to improve memory efficiency.\
  Before
  ```
  @Entity
  public class Patient {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;
  ...
  public void setPhone(String phone) {
     this.phone = phone;
  }
  ```
  After
  ```
  public record Patient(Integer id, String firstName, String lastName, String birthday, String gender, String address, String phone) {}
  ```
- Avoid redundancy and limit memory consumption.\
   Centralize common entities in a shared module, for example: model Patient and Note.
- Merge lightweight microservices into a modular monolith: this would drastically reduce CPU/RAM usage and simplify deployment.
- Choose a host with good environmental performance and powered by renewable energy.
   
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
   ```Bash
   git clone https://github.com/wysgar/P9MediLabo.git
   ```

3. Compilation
   - Run the following command in each microservices to build their .jar
   ```Bash
   mvn -DskipTests=true package
   ```

5. Docker compose
   - Run the following command in the project root to build the docker images and launch the containers
   ```Bash
   docker-compose up --build
   ```

7. Access the application
   - [Site](http://localhost:8080/login)
   - Credentials :
      - Username : user / Password : password
      - Username : admin / Password : password
