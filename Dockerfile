# Step 1: Use a base image with JDK for building the application
FROM openjdk:17-jdk-slim AS builder

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the project files to the container
COPY . .

# Step 4: Grant execution permission to the Gradle wrapper
RUN chmod +x ./gradlew

# Step 5: Build the project using the Gradle wrapper
RUN ./gradlew clean build -x test

# Step 6: Use a minimal base image for running the application
FROM openjdk:17-jdk-slim

# Step 7: Set the working directory for the runtime container
WORKDIR /app

# Step 8: Copy the built JAR from the builder stage to the runtime container
COPY --from=builder /app/build/libs/DevOps-1.0.0.jar app.jar

# Step 9: Expose the application port (replace 8080 with your app's port if different)
EXPOSE 8080

# Step 10: Define the entry point for running the application
ENTRYPOINT ["java", "-jar", "app.jar"]
