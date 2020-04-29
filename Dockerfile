FROM openjdk:11

VOLUME /tmp

ADD build/libs/CrudBooks-0.0.1-SNAPSHOT.jar CrudBooks-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=dev", "-jar", "CrudBooks-0.0.1-SNAPSHOT.jar"]