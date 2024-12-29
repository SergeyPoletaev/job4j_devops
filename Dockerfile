FROM gradle:8.11.1-jdk21 as builder

ARG GRADLE_REMOTE_CACHE_URL
ARG GRADLE_REMOTE_CACHE_PUSH
ARG GRADLE_REMOTE_CACHE_USERNAME
ARG GRADLE_REMOTE_CACHE_PASSWORD

ENV GRADLE_REMOTE_CACHE_URL=$GRADLE_REMOTE_CACHE_URL \
    GRADLE_REMOTE_CACHE_PUSH=$GRADLE_REMOTE_CACHE_PUSH \
    GRADLE_REMOTE_CACHE_USERNAME=$GRADLE_REMOTE_CACHE_USERNAME \
    GRADLE_REMOTE_CACHE_PASSWORD=$GRADLE_REMOTE_CACHE_PASSWORD

WORKDIR /job4j_devops
COPY . .
RUN gradle build -x test

FROM openjdk:21-ea-slim-bullseye
COPY --from=builder /job4j_devops/build/libs/DevOps-1.0.0.jar DevOps-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "DevOps-1.0.0.jar"]
