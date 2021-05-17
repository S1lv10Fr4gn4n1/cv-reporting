# cv-reporting

### Description
This project consumes the events from RabbitMQ queue `hello-cv1` and `hello-cv2`. For now, it does nothing but fake a heavy process for each message.

It will be used by the cluster [cv-k8s](https://github.com/s1lv10fr4gn4n1-org/cv-k8s).


### Requirements
- [Gradle](https://gradle.org/)
- [JDK 1.8](https://www.oracle.com/de/java/technologies/javase/javase-jdk8-downloads.html)
- [Spring Native](https://github.com/spring-projects-experimental/spring-native)


### How to build
Run local using gradle
- `./gradlew bootRun`

Build container image 
- `./gradlew bootBuildImage`