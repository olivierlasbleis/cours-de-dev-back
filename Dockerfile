FROM openjdk:8
ADD target/site-professionnel-cours-api.jar site-professionnel-cours-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "site-professionnel-cours-api.jar"]