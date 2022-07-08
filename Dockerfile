FROM openjdk:8
EXPOSE 9090
ADD target/spring-boot-jpa-demo.jar spring-boot-jpa-demo.jar
ENTRYPOINT ["java","-jar","/spring-boot-jpa-demo.jar"]