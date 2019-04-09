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
      case Flavor762141944277 => get1(url, bBOutputRequest)
      case Flavor811125320161 => get2(url, bBOutputRequest)
      case Flavor913136672544 => get2(url, bBOutputRequest)
    }

  }

  def get1(url: String, request: BBOutputRequest): Future[Response[String]] = {
     val map = (Map("number" -> request.number,
                   )
       ++ request.from.map(x => ("from",x.format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss"))))
       ++ request.to.map(x => ("to",x.format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss"))))
       ++ request.outputFormat.map(("output",_))
       )
     val requestUrl = uri"${url}/bboutput?${map}"
     sttp.get(requestUrl).send()
  }

  def get2(url: String, request: BBOutputRequest): Future[Response[String]] = {
    val map = (Map("number" -> request.number,
    )
      ++ request.from.map(x => ("From",x.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
      ++ request.to.map(x => ("To",x.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
      ++ request.outputFormat.map(("Output",_))
      )
    val requestUrl = uri"${url}/bboutput?${map}"
    System.err.println(s"url=${requestUrl}")
    sttp.get(requestUrl).send()
  }


}
