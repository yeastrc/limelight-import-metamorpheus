FROM amazoncorretto:11-alpine-jdk

COPY build/libs/metamorpheus2LimelightXML.jar  /usr/local/bin/metamorpheus2LimelightXML.jar

ENTRYPOINT ["java", "-jar", "/usr/local/bin/metamorpheus2LimelightXML.jar"]