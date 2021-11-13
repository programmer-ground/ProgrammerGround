# Build Stage
FROM azul/zulu-openjdk-alpine:11.0.12 as build
WORKDIR /workspace/app

# Only copy dependency-related files
COPY gradle gradle
COPY build.gradle settings.gradle gradlew ./

# Copy Sourcefile
COPY src src

# build soruce file with dependnecies
RUN ./gradlew build -x test
RUN mkdir -p build/libs/dependency && (cd build/libs/dependency; jar xf ../*.jar)

# Runtime Stage
FROM azul/zulu-openjdk-alpine:11.0.12
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/libs/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.pg.programmerground.ProgrammergroundApplication"]