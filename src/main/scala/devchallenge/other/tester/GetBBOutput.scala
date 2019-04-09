package devchallenge.other.tester

import java.time.format.DateTimeFormatter

import com.softwaremill.sttp._
import com.softwaremill.sttp.akkahttp.AkkaHttpBackend

import scala.concurrent.Future

object GetBBOutput {

  implicit val backend: SttpBackend[Future, Nothing]  = AkkaHttpBackend()


  def get(url:String, bBOutputRequest: BBOutputRequest, requestFlavor: RequestFlavor): Future[Response[String]] = {
    requestFlavor match {
      case Flavor520154416683 => Future failed (new RuntimeException("not implemented"))
      case Flavor762141944277 => getJson(url, bBOutputRequest)
      case _ => ???
    }

  }

  def getJson(url: String, request: BBOutputRequest): Future[Response[String]] = {
     val map = (Map("number" -> request.number,
                   )
       ++ request.from.map(x => ("from",x.format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss"))))
       ++ request.to.map(x => ("to",x.format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss"))))
       ++ request.outputFormat.map(("output",_))
       )
     val requestUrl = uri"${url}/bboutput?${map}"
     sttp.get(requestUrl).send()
  }

}
