package devchallenge.other.tester

import java.time.format.DateTimeFormatter

import com.softwaremill.sttp._
import com.softwaremill.sttp.akkahttp.AkkaHttpBackend

import scala.concurrent.Future

object PostBBInput {

  implicit val backend = AkkaHttpBackend()

  def apply(url: String, bBInput: BBInput): Future[Response[String]] = {
    /*
    val map = (Map(
      "number" -> bBInput.number,
      "imei" -> bBInput.imei,
      "coordinates" -> s"[${bBInput.coordinates.latitude}, ${bBInput.coordinates.longitude}]"
    ) ++ bBInput.ip.map( s => ("ip",s)).toMap
      ++ bBInput.timestamp.map( t => ("timestamp", t.format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss"))))
      )
    val request = sttp.post(uri"${url}/bbinput").body(map)
    request.send()
    */
    ???
  }


}
