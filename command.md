<!-- Create a zookeeper cluster -->

docker run -d --name zookeeper -p 2181:2181 -e ZOOKEEPER_CLIENT_PORT=2181 -e ZOOKEEPER_TICK_TIME=2000 confluentinc/cp-zookeeper:7.5.0

<!-- kafka broker instance running using the zookeeper cluster -->

docker run -d --name kafka -p 9092:9092 -e KAFKA_BROKER_ID=1 -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 --link zookeeper confluentinc/cp-kafka:7.5.0

<!-- interactive mode with kafka bash -->

docker exec -it kafka bash

<!--  to create a Topic -->

kafka-topics --create --topic my-topic --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1

<!-- List all the topic -->

kafka-topics --list --bootstrap-server localhost:9092

<!-- Describe the topic -->

kafka-topics --describe --topic my-topic --bootstrap-server localhost:9092

<!-- To play with realtime producer  -->

kafka-console-producer --topic my-topic --bootstrap-server localhost:9092

<!-- Kafka consumer -->

kafka-console-consumer --topic my-topic --bootstrap-server localhost:9092 --from-beginning

<!-- Kafka Consumer Group for no redundant messaging  -->

kafka-console-consumer --topic my-topic --bootstrap-server localhost:9092 --group my-group --from-beginning

<!-- Key in Producer  -->

kafka-console-producer --topic my-topic --bootstrap-server localhost:9092 --property "parse.key=true" --property "key.separator=:"

<!-- Kafka Group Describe  -->

kafka-consumer-groups --bootstrap-server localhost:9092 --describe --group my-group
