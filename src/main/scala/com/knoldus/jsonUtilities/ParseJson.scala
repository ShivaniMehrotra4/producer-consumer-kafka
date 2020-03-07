package com.knoldus.jsonUtilities

import com.knoldus.model.User
import net.liftweb.json.{DefaultFormats, JValue}

import scala.io.{BufferedSource, Source}

class ParseJson {

  def parseData(): List[User] = {

    implicit val formats: DefaultFormats.type = DefaultFormats

    val filePath = "/home/knoldus/KAFKA/PersonDetails.json"
    val jsonData: BufferedSource = Source.fromFile(filePath)
    val parsedJson: JValue = net.liftweb.json.parse(jsonData.mkString)
    //println(parsedJson.children.map(user => user.extract[User]))
    parsedJson.children.map(user => user.extract[User])
  }
}