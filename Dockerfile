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

COPY build.gradle.kts settings.gradle.kts gradle.properties .
RUN mkdir -p gradle
COPY ./gradle/libs.versions.toml ./gradle
RUN gradle --no-daemon dependencies

COPY . .
RUN gradle --no-daemon build -x test
RUN jar xf /job4j_devops/build/libs/DevOps-1.0.0.jar

RUN jdeps --ignore-missing-deps -q \
    --recursive \
    --multi-release 21 \
    --print-module-deps \
    --class-path 'BOOT-INF/lib/*' \
    /job4j_devops/build/libs/DevOps-1.0.0.jar > deps.info
RUN jlink \
    --add-modules $(cat deps.info) \
    --strip-debug \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output /slim-jre

FROM debian:bookworm-slim
ENV JAVA_HOME /user/java/jdk21
ENV PATH $JAVA_HOME/bin:$PATH
COPY --from=builder /slim-jre $JAVA_HOME
COPY --from=builder /job4j_devops/build/libs/DevOps-1.0.0.jar .
ENTRYPOINT ["java", "-jar", "DevOps-1.0.0.jar"]
