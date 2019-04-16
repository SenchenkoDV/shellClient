FROM openjdk:8
ADD target/shellClient-1.0-SNAPSHOT.jar shellClient-1.0-SNAPSHOT.jar
EXPOSE 8022
CMD java -jar shellClient-1.0-SNAPSHOT.jar