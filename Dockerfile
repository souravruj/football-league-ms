FROM registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift
ADD target/football-league.jar football-league.jar
EXPOSE 8082
ENTRYPOINT ["java". "-jar", "football-league.jar"]
