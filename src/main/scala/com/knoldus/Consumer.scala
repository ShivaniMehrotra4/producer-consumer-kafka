package com.knoldus

import java.util
import java.util.Properties

import com.knoldus.model.User

import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.KafkaConsumer


object Consumer extends App {


  def readFromKafka(topic: String): Unit = {
    //val topic = "json"
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("auto.offset.rest", "latest")
    props.put("group.id", "consumer-group")

    val consumer = new KafkaConsumer[String, String](props)

    consumer.subscribe(util.Arrays.asList(topic))

    while (true) {
      val records = consumer.poll(100).asScala
      for (record <- records.iterator) {
        println(record.value())
      }
    }
  }
    readFromKafka("json")

}
