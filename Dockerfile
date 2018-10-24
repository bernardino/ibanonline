FROM dockerfile/java:oracle-java8

COPY build/libs/BackendTest-0.0.1-SNAPSHOT.jar myibanwallet.jar

EXPOSE 8080

CMD java -jar BackendTest-0.0.1-SNAPSHOT.jar