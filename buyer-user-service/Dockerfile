FROM openjdk:11
ADD ./target/buyer-user-service-0.0.1-SNAPSHOT.jar /usr/src/buyer-user-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "buyer-user-service-0.0.1-SNAPSHOT.jar"]
