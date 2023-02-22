FROM openjdk:11
ADD ./target/cart-service-0.0.1-SNAPSHOT.jar /usr/src/cart-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "cart-service-0.0.1-SNAPSHOT.jar"]
