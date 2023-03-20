FROM maven:3.8.1-amazoncorretto-11

COPY . /CarApp/

RUN cd /CarApp && mvn clean install
RUN cp /CarApp/target/CarApp-1.0-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

