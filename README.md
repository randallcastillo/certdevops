# CI/CD Environment
### DevOps Specialist Certification
### TechGround, UCreativa
---
## The Service
This is a RESTful web service with Spring Boot Actuator.
The service accepts the following HTTP GET request:

```
$ curl http://localhost:9998/greet?name=John
```
It responds with the following JSON:
```
{
    "id": 1,
    "content": "Hello, John!"
}
```
## Run the Service
The `SpringApplication.run()` command knows how to launch the web application. All you need to do is run the following command:
```
$ mvn clean package && java -jar target\certdevops-0.0.1-SNAPSHOT.jar
```
Wait for the server to start, open another terminal, and try the following command:
```
$ curl localhost:9997/actuator/health
{"status":"UP"}
```
The status is `UP`, so the actuator service is running.