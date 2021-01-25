# Build Stage
FROM gradle:6.8.0-jdk11 AS builder
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

# Only copy dependency-related files
COPY build.gradle settings.gradle ./

# Copy Sourcefile
COPY src src

# build soruce file with dependnecies
#RUN gradle cleanQuerydslSourcesDir
RUN gradle clean build --console=plain -x test

# Runtime Stage
FROM openjdk:11-jre-slim

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
ARG buildDir=$APP_HOME/build/unpack

COPY --from=builder ${buildDir}/lib BOOT-INF/lib
COPY --from=builder ${buildDir}/app .

EXPOSE 8080

CMD ["java", "-Xms400M", "-Xmx400M", "org.springframework.boot.loader.JarLauncher"]