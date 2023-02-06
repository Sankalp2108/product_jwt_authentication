#FROM openjdk
#COPY target/Product-0.0.1-SNAPSHOT.jar Product-0.0.1-SNAPSHOT.jar
#EXPOSE 8080
#ENTRYPOINT [ "java" ,”-Dspring.data.mongodb.uri=mongodb://mongo/test”, “-Djava.security.egd=file:/dev/./urandom”, "-jar" , "Product-0.0.1-SNAPSHOT.jar"]

# FROM openjdk
# VOLUME /tmp
# ADD target/Product-0.0.1-SNAPSHOT.jar Product-0.0.1-SNAPSHOT.jar
# EXPOSE 8080
# RUN bash -c ‘touch /Product-0.0.1-SNAPSHOT.jar’
# ENTRYPOINT [“java”,”-Dspring.data.mongodb.uri=mongodb://mongo/test”, “-Djava.security.egd=file:/dev/./urandom”,”-jar”,”Product-0.0.1-SNAPSHOT.jar”]

FROM openjdk
VOLUME /main-app
ADD target/Product-0.0.1-SNAPSHOT.jar Product-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","Product-0.0.1-SNAPSHOT.jar"]