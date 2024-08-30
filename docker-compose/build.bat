echo "Build Microservices with Gradle and build Docker Images"
.\gradlew clean build ^
& docker-compose build
