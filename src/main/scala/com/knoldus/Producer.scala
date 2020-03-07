package com.knoldus

import java.util.Properties

import com.knoldus.jsonUtilities.ParseJson
import com.knoldus.model.User
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object Producer extends App {

  val parseJson = new ParseJson

  def writeToKafka(topic: String): Unit = {

    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)
    //val topic = "json"

    val msg = "Hello!!"
    //val jsonResult = parseJson.parseData()
    val record = new ProducerRecord[String,String](topic, "key",msg)
    producer.send(record)
    producer.close()

  }

  writeToKafka("json")
}
