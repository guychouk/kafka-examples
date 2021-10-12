package kafka.examples

import java.util.regex.Pattern
import java.util.{Collections, Properties}

import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.{KafkaConsumer, ConsumerConfig}

object KafkaConsumerApp extends App {

  val topics = List("example-input-topic")
  val props:Properties = new Properties()

  props.put(ConsumerConfig.GROUP_ID_CONFIG, "test")
  props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true")
  props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000")
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092")
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer") 
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")

  val consumer = new KafkaConsumer(props)
  try {
    consumer.subscribe(topics.asJava)
    while (true) {
      val records = consumer.poll(10)
      for (record <- records.asScala) {
        println("Topic: " + record.topic() + 
          ",Key: " + record.key() +  
          ",Value: " + record.value() +
          ", Offset: " + record.offset() + 
          ", Partition: " + record.partition())
      }
    }
  } catch {
    case e:Exception => e.printStackTrace()
    } finally {
      consumer.close()
    }
}
