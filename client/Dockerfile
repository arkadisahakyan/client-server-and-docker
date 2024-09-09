FROM openjdk:17-alpine

WORKDIR /app

COPY ./src .

RUN javac Client.java

CMD ["java", "Client"]
