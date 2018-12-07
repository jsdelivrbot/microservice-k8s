#!/usr/bin/env bash

docker-compose up -d --force-recreate 
sleep 10
docker exec k8s-kafka ./opt/bitnami/kafka/bin/kafka-topics.sh  --zookeeper zookeeper:2181\
    --create --topic inboundTopic --partitions 1 --replication-factor 1
docker exec k8s-kafka ./opt/bitnami/kafka/bin/kafka-topics.sh  --zookeeper zookeeper:2181\
    --create --topic outboundTopic --partitions 1 --replication-factor 1
echo "Active Topics:"	
docker exec k8s-kafka ./opt/bitnami/kafka/bin/kafka-topics.sh --list  --zookeeper zookeeper:2181
