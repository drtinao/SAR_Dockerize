# MAVEN BUILD PART
# pick up maven image from Docker hub
FROM maven:latest as maven

#set default working directory - maven build
ENV WORKING_DIR_MAVEN=/usr/src/eurosoftware_demo_src
WORKDIR $WORKING_DIR_MAVEN

# copy all content from this folder to container
COPY . $WORKING_DIR_MAVEN

# build maven app inside container
RUN mvn package

# JAVA RUN PART
# pick up jdk image from Docker hub
FROM openjdk:latest

#pick up jar file produced by maven build process
ARG JAR_FILE=projects-service-0.0.1-SNAPSHOT.jar
ENV WORKING_DIR_JAVA=/opt/eurosoftware_demo_jars

#set working directory - java jars
WORKDIR $WORKING_DIR_JAVA

#run microservice
COPY --from=maven /usr/src/eurosoftware_demo_src/target/${JAR_FILE} $WORKING_DIR_JAVA
ENTRYPOINT ["java", "-jar", "projects-service-0.0.1-SNAPSHOT.jar"]

