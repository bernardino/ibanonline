FROM openjdk:8u181-jdk-stretch

ADD build/libs/backendtest-0.0.1-SNAPSHOT.jar myibanwallet.jar
ADD application.yml application.yml

EXPOSE 8080

CMD java -jar myibanwallet.jar