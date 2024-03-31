FROM eclipse-temurin:21

WORKDIR /opt/application

env JAVA_OPTS="-Xms2048m -Xmx8192m"

EXPOSE 8080
EXPOSE 6011

CMD ./gradlew quarkusDev -Ddebug=6011 -DdebugHost=0.0.0.0 -Dquarkus.analytics.disabled=true
