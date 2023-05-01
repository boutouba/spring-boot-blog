FROM openjdk:17-jdk-slim-buster
#WORKDIR /app
#
#COPY build/lib/* build/lib/
#COPY build/libs/post-0.0.1-SNAPSHOT.jar build/
##COPY . .
#
#WORKDIR /app/build
#ENTRYPOINT java -jar post-0.0.1-SNAPSHOT.jar
COPY build/libs/post-0.0.1-SNAPSHOT.jar post-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/post-0.0.1-SNAPSHOT.jar"]
