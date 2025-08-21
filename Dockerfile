FROM eclipse-temurin:23-jdk-alpine
RUN apk add curl jq
WORKDIR /home/selenium-docker
ADD target/docker-resources ./
ADD runner.sh runner.sh
ENTRYPOINT sh runner.sh
# Your application code and build steps here