# Kafka Scala Examples

This repo serves as an example for two standard use cases for Kafka:
- Kafka stream setup to read input from one topic and output to another.
- Kafka consumer to consume data from a topic and process it.

These examples are written in Scala, which was a bit diffuclt for me to find online for some reason, so I added them here for easy reference.

## Prerequisites
This repository uses [asdf](https://asdf-vm.com/) to manage the versions of tools used in this project.
Refer to asdf's [docs](https://asdf-vm.com/guide/getting-started.html#_1-install-dependencies) for how to install it.

- Install the relevant tools:
  ```shell
  asdf plugin add scala 
  asdf plugin add sbt 
  asdf install
  ```
- Run `docker-compose up` to setup a Zookeeper and Kafka nodes locally on `localhost:9200`
- Create two topics:
  - `docker-compose exec kafka kafka-topics.sh --create --zookeeper zookeeper:2181 --partitions 1 --replication-factor 1 --topic example-input-topic`
  - `docker-compose exec kafka kafka-topics.sh --create --zookeeper zookeeper:2181 --partitions 1 --replication-factor 1 --topic example-output-topic`

## Running
Run `sbt run`, choose the desired example, and use a tool like [kcat](https://github.com/edenhill/kcat) for producing and consuming messages.
