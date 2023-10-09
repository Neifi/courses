FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package

FROM gcr.io/distroless/java17-debian11

WORKDIR /app

COPY --from=build /app/target/courses-0.0.1-SNAPSHOT.jar .

CMD ["courses-0.0.1-SNAPSHOT.jar"]
