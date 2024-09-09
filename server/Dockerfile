FROM openjdk:17-alpine

WORKDIR /app

COPY ./src .

RUN javac Server.java

CMD ["java", "Server"]
