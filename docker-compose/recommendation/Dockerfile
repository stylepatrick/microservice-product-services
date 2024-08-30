# Stage 1: Extract layers from the JAR file
FROM eclipse-temurin:17.0.11_9-jre as builder
WORKDIR extracted

# Add the JAR file
ADD ./build/libs/*.jar app.jar

# Extract the layers using layertools
RUN java -Djarmode=layertools -jar app.jar extract

# Stage 2: Build the final image
FROM eclipse-temurin:17.0.11_9-jre
WORKDIR application

# Copy the extracted layers
COPY --from=builder extracted/dependencies/ ./
COPY --from=builder extracted/spring-boot-loader/ ./
COPY --from=builder extracted/snapshot-dependencies/ ./
COPY --from=builder extracted/application/ ./

# Set the entry point to use the JarLauncher
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]