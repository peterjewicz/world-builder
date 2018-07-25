FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/worldbuilder.jar /worldbuilder/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/worldbuilder/app.jar"]
