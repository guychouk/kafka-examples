# Kafka Scala Examples

This repo serves as an example for two standard use cases for Kafka:
- Kafka stream setup to read input from one topic and output to another.
- Kafka consumer to consume data from a topic and process it.

These examples are written in Scala, which was a bit diffuclt for me to find online for some reason, so I added them here for easy reference.

Another addition is a ready-to-use [docker-compose.yml](./docker-compose.yml) for setting up Kafka locally using Docker containers.

## Prerequisites
If you already have sbt and Scala installed, simply run `sbt run` and choose one of the example applications to run.
If you don't have sbt and Scala installed, I suggest you follow the guide below, since it will get you up and running very quickly.

This repository uses [asdf](https://asdf-vm.com/) to manage the versions of tools used in this project.

Refer to asdf's [docs](https://asdf-vm.com/guide/getting-started.html#_1-install-dependencies) for how to install it.
For the lazy folks out there, here is the installation for Mac OS:

```shell
brew install asdf
echo -e "\n. $(brew --prefix asdf)/libexec/asdf.sh" >> ${ZDOTDIR:-~}/.zshrc
```

- Install the relevant tools:
  ```shell
  asdf plugin add scala 
  asdf plugin add sbt 
  asdf install
  ```
- Run `docker-compose up` to setup a Zookeeper and Kafka nodes locally on `localhost:9200`

- Create two topics:
	```shell
	# One for input
  docker-compose exec kafka kafka-topics.sh --create --zookeeper zookeeper:2181 --partitions 1 --replication-factor 1 --topic example-input-topic

	# One for output
  docker-compose exec kafka kafka-topics.sh --create --zookeeper zookeeper:2181 --partitions 1 --replication-factor 1 --topic example-output-topic
	```

## Running
Run `sbt run`, choose the desired example, and use a tool like [kcat](https://github.com/edenhill/kcat) for producing and consuming messages, like so:

```shell
# -P for producer
kcat -b localhost:9092 -t example-input-topic -P

# -C for consumer
kcat -b localhost:9092 -t example-input-topic -C
```
