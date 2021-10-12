package kafka.examples

import java.time.Duration
import java.util.Properties

import org.apache.kafka.streams.scala.kstream._
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}

object KafkaStreamsApp extends App {

  import org.apache.kafka.streams.scala.Serdes._
  import org.apache.kafka.streams.scala.ImplicitConversions._

  val inputTopic = "example-input-topic"
  val outputTopic = "example-output-topic"
  val bootstrapServers = if (args.length > 0) args(0) else "localhost:9092"

  val config: Properties = {
    val p = new Properties()
    p.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-course")
    p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
    p
  }

  val builder = new StreamsBuilder
  val textLines: KStream[Array[Byte], String] = builder.stream[Array[Byte], String](inputTopic)

  textLines
    .mapValues(_.toUpperCase())
    .to(outputTopic)

  val streams: KafkaStreams = new KafkaStreams(builder.build(), config)

  streams.start()

  sys.ShutdownHookThread {
    streams.close(Duration.ofSeconds(5))
  }
}
