package com.knoldus

import java.io.{BufferedWriter, File, FileWriter}
import java.util
import java.util.Properties
import com.knoldus.model.{Constants, User}
import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.KafkaConsumer


object Consumer extends App {

  /**
   * This function reads json parsed data from kafka pipeline.
   *
   * @param topic - topic pipeline
   */
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
      val records = consumer.poll(Constants.timeout).asScala
      for (record <- records.iterator) {
        //println(record.value())
        writeUserToFile(record.value())
      }

    }
  }

  readFromKafka("json")

  /**
   * This function writes json parsed data from kafka pipeline to a file.
   *
   * @param user - parsed user data
   */
  def writeUserToFile(user: String): Unit = {
    val writer = new BufferedWriter(new FileWriter(new File("./src/main/resources/userParsedData.txt")
      , true))
    writer.append(user)
    writer.close()

  }
}
