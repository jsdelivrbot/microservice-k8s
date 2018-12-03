FROM openjdk:8-jdk-alpine

ARG SERVICE=rulerunner-service
ARG VERSION=1.0.0-SNAPSHOT

ENV BASEDIR=/app
ENV SERVICE=${SERVICE}
ENV VERSION=${VERSION}

RUN apk --no-cache add bash curl vim less

ADD build/libs/${SERVICE}-${VERSION}.jar ${BASEDIR}/${SERVICE}.jar


EXPOSE 8080

CMD java ${JAVA_OPTS} -jar ${BASEDIR}/${SERVICE}.jar
