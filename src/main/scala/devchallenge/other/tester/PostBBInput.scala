package devchallenge.other.tester

import java.time.format.DateTimeFormatter

import com.softwaremill.sttp._
import com.softwaremill.sttp.akkahttp.AkkaHttpBackend

import scala.concurrent.Future

object PostBBInput {

  implicit val backend: SttpBackend[Future, Nothing]  = AkkaHttpBackend()

  def post(url:String, bBInput: BBInput, requestFlavor: RequestFlavor): Future[Response[String]] = {
    requestFlavor match {
      case Flavor520154416683 => postJson1(url,bBInput)
      case Flavor762141944277 => postForm2(url,bBInput)
      case Flavor811125320161 => postJson3(url,bBInput)
      case Flavor913136672544 => postJson4(url,bBInput)
    }
  }

  def postForm(url: String, bBInput: BBInput): Future[Response[String]] = {
    val map = (Map(
      "number" -> bBInput.number,
      "imei" -> bBInput.imei,
      "coordinates" -> s"[${bBInput.coordinates.latitude}, ${bBInput.coordinates.longitude}]"
    ) ++ bBInput.ip.map( s => ("ip",s)).toMap
      ++ bBInput.timestamp.map( t => ("timestamp", t.format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss"))))
      )
    val request = sttp.post(uri"${url}/bbinput").body(map)
    request.send()
  }

  def postJson1(url: String, bBInput: BBInput): Future[Response[String]] = {
    val optIp = bBInput.ip.map(s =>
      s""",
        | "ip" : "${s}"
      """.stripMargin).getOrElse("")
    val optTimestamp = bBInput.timestamp.map{t =>
      s"""
         |,
         |"timestamp": "${t.format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss"))}"
       """.stripMargin}.getOrElse("")
    val json = s"""
      |{
      | "number" : "${bBInput.number}"
      | ,
      | "imei" : "${bBInput.imei}"
      | ,
      | "coordinates" : [ ${bBInput.coordinates.latitude}, ${bBInput.coordinates.longitude} ]
      | ${optIp}
      | ${optTimestamp}
      |}
    """.stripMargin
    System.err.println(s"json=${json}")
    val request = sttp.post(uri"${url}/bbinput").contentType("application/json").body(json)
    request.send()
  }

  def postJson2(url: String, bBInput: BBInput): Future[Response[String]] = {
    val optIp = bBInput.ip.map(s =>
      s""",
         | "ip" : "${s}"
      """.stripMargin).getOrElse("")
    val optTimestamp = bBInput.timestamp.map{t =>
      s"""
         |,
         |"timestamp": "${t.format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss"))}"
       """.stripMargin}.getOrElse("")
    val json = s"""
                  |{
                  | "number" : "${bBInput.number}"
                  | ,
                  | "imei" : "${bBInput.imei}"
                  | ,
                  | "coordinates" : {
                  |    "latitude" : ${bBInput.coordinates.latitude},
                  |    "longitude" : ${bBInput.coordinates.longitude}
                  | }
                  | ${optIp}
                  | ${optTimestamp}
                  |}
    """.stripMargin
    System.err.println(s"json=${json}")
    val request = sttp.post(uri"${url}/bbinput").contentType("application/json").body(json)
    request.send()
  }


  def postForm2(url: String, bBInput: BBInput): Future[Response[String]] = {
    val map = (Map(
      "phone_number" -> bBInput.number,
      "imei" -> bBInput.imei,
      "latitude" -> s"${bBInput.coordinates.latitude}",
      "longitude" -> s"${bBInput.coordinates.longitude}",
    ) ++ bBInput.ip.map( s => ("ip_address",s)).toMap
      ++ bBInput.timestamp.map( t => ("date_time", t.format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss"))))
      )
    val request = sttp.post(uri"${url}/bbinput").body(map)
    request.send()
  }

  def postJson3(url: String, bBInput: BBInput): Future[Response[String]] = {
    val optIp = bBInput.ip.map(s =>
      s""",
         | "IP" : "${s}"
      """.stripMargin).getOrElse("")
    val optTimestamp = bBInput.timestamp.map{t =>
      s"""
         |,
         |"timestamp": "${t.format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss"))}"
       """.stripMargin}.getOrElse("")
    val json = s"""
                  |{
                  | "number" : "${bBInput.number}"
                  | ,
                  | "IMEI" : "${bBInput.imei}"
                  | ,
                  | "Coordinares" : [
                  |    ${bBInput.coordinates.latitude},
                  |    ${bBInput.coordinates.longitude}
                  | ]
                  | ${optIp}
                  | ${optTimestamp}
                  |}
    """.stripMargin
    System.err.println(s"json=${json}")
    val request = sttp.post(uri"${url}/bbinput").contentType("application/json").body(json)
    request.send()
  }

  def postJson4(url: String, bBInput: BBInput): Future[Response[String]] = {
    val optIp = bBInput.ip.map(s =>
      s""",
         | "IP" : "${s}"
      """.stripMargin).getOrElse("")
    val optTimestamp = bBInput.timestamp.map{t =>
      s"""
         |,
         |"timestamp": "${t.format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss"))}"
       """.stripMargin}.getOrElse("")
    val json = s"""
                  |{
                  | "number" : "+380${bBInput.number.padTo(8,0).mkString}"
                  | ,
                  | "IMEI" : "${bBInput.number.padTo(16,"0").mkString}"
                  | ,
                  | "Coordinares" : [
                  |    ${bBInput.coordinates.latitude},
                  |    ${bBInput.coordinates.longitude}
                  | ]
                  | ${optIp}
                  | ${optTimestamp}
                  |}
    """.stripMargin
    System.err.println(s"json=${json}")
    val request = sttp.post(uri"${url}/bbinput").contentType("application/json").body(json)
    request.send()
  }



}
