FROM openjdk:11
ADD ./target/seller-user-service-0.0.1-SNAPSHOT.jar /usr/src/seller-user-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "seller-user-service-0.0.1-SNAPSHOT.jar"]
