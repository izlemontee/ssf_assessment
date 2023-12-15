FROM maven:3-eclipse-temurin-21

#working and target dir
WORKDIR /app

#copy all the required stuff
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn
COPY src src


#build app
RUN mvn package -Dmaven.test.skip=true

ENV PORT=8080 
ARG SPRING_REDIS_HOST
RUN echo $SPRING_REDIS_HOST
ARG SPRING_REDIS_PORT
RUN echo $SPRING_REDIS_PORT
ARG SPRING_REDIS_USER
RUN echo $SPRING_REDIS_USER
ARG SPRING_REDIS_PASSWORD
RUN echo $SPRING_REDIS_PASSWORD

EXPOSE ${PORT} 
ENTRYPOINT SERVER_PORT=${PORT}  java -jar target/eventmanagement-0.0.1-SNAPSHOT.jar