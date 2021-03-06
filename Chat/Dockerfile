# Build Stage
FROM gradle:6.8.0-jdk11 AS builder
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

# Only copy dependency-related files
COPY build.gradle settings.gradle ./

# Donwload dependencies. docker layer caching
RUN gradle clean build --no-daemon > /dev/null 2>&1 || true

# Copy Source File
COPY src src

# Build soruce file
RUN gradle clean build -x test --console=plain

# Extract Layerd Jar
RUN java -Djarmode=layertools -jar build/libs/*.jar extract

# Runtime Stage
FROM openjdk:11-jre-slim
# Working Directory
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

COPY --from=builder ${APP_HOME}/dependencies/ ./
COPY --from=builder ${APP_HOME}/spring-boot-loader/ ./
COPY --from=builder ${APP_HOME}/snapshot-dependencies/ ./
COPY --from=builder ${APP_HOME}/application/ ./

EXPOSE 8081
CMD ["java", "-Xms700M", "-Xmx700M", "org.springframework.boot.loader.JarLauncher"]
