#!/usr/bin/env bash

docker-compose up -d
sleep 3
docker exec k8s-kafka ./opt/bitnami/kafka/bin/kafka-topics.sh  --zookeeper zookeeper:2181\
    --create --topic inboundTopic --partitions 1 --replication-factor 1
docker exec k8s-kafka ./opt/bitnami/kafka/bin/kafka-topics.sh  --zookeeper zookeeper:2181\
    --create --topic outboundTopic --partitions 1 --replication-factor 1
