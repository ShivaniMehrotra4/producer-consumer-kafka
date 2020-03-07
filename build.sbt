name := "producer-consumer-kafka"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies += "org.apache.kafka" %% "kafka" % "2.4.0"

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.4.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % "test"

libraryDependencies += "net.liftweb" %% "lift-json" % "3.4.0"