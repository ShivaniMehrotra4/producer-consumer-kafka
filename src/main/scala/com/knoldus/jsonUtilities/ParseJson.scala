package com.knoldus.jsonUtilities

import com.knoldus.model.User
import net.liftweb.json.{DefaultFormats, JValue}

class ParseJson {

  /**
   * This function returns json data as string.
   * @return - string user data json.
   */
  def getJsonData: String = {
    """  [
      {
        "id": 0001,
        "name": "shivani",
        "age": 24,
        "address": "Sahibabad"

      }
    ]
  """
  }

  /**
   * This function parses json data.
   * @return list of users
   */
  def parseData(): List[User] = {

    implicit val formats: DefaultFormats.type = DefaultFormats

    val jsonData = getJsonData
    //    val filePath = "./src/main/resources/PersonDetails.json"
    //    val jsonData = Source.fromFile(filePath)
    val parsedJson: JValue = net.liftweb.json.parse(jsonData)
    parsedJson.children.map(user => user.extract[User])
  }
}