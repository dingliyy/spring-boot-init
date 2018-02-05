FROM 172.31.3.12:5000/tomcat
VOLUME /tmp
ADD microservice-spring-boot-admin-0.0.1-SNAPSHOT.jar spring-boot-admin.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","spring-boot-admin.jar"]
EXPOSE 64000