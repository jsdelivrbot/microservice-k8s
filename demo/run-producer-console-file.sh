docker exec -it k8s-kafka ./opt/bitnami/kafka/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic inboundTopic < FIXMsgs.txt
