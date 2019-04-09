package devchallenge.other.tester

import java.time.LocalDateTime

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.util.Random

sealed trait RequestFlavor
case object Flavor520154416683 extends RequestFlavor
case object Flavor762141944277 extends RequestFlavor
case object Flavor811125320161 extends RequestFlavor
case object Flavor913136672544 extends RequestFlavor


object ExternalTest {


  val flavor: RequestFlavor = Flavor811125320161

  def main(args: Array[String]):Unit = {
    val baseUrl = if (args.length < 2) {
      val defaultUrl = "http://127.0.0.1:8080/ourell"
      Console.println(s"use default app:  ${defaultUrl}")
      defaultUrl
    } else {
      args(1)
    }
    Console.println(s"baseUrl=${baseUrl}");

    val time = LocalDateTime.now()
    val f1 = postOne(baseUrl,time)
    val r1 = Await.result(f1,5 seconds)
    System.out.println(s"one test: $r1")

    val f2 = getOneWithoutTime(baseUrl)
    val r2 = Await.result(f2,5 seconds)
    System.out.println(s"one test: $r2")

    val f3 = getOneCSV(baseUrl)
    val r3 = Await.result(f3,5 seconds)
    System.out.println(s"one xml test: $r3")

    val f4 = getOneWithinTime(baseUrl,time)
    val r4 = Await.result(f4,5 seconds)
    System.out.println(s"one within time test: $r4")


  }

  val random = new Random()

  import scala.concurrent.ExecutionContext.Implicits.global

  def postOne(url:String, time: LocalDateTime):Future[Boolean] = {
    val bBInput = BBInputGenerator.generateInitial(1,random,BBInputGeneratorOptions(startTime = time))
    PostBBInput.post(url,bBInput, flavor).map{x =>
      Console.println(s"POST: received ${x.body}")
      x.code == 200
    }
  }

  def getOneWithoutTime(url:String):Future[Boolean] = {
    val bBOutputRequest = BBOutputRequest(number = "1")
    GetBBOutput.get(url,bBOutputRequest,flavor).map{ x =>
      Console.println(s"GET: received ${x.body}")
      x.code == 200
    }
  }

  def getOneCSV(url:String):Future[Boolean] = {
    val bBOutputRequest = BBOutputRequest(number = "1", outputFormat=Some("CSV"))
    GetBBOutput.get(url,bBOutputRequest,flavor).map{ x =>
      Console.println(s"GET XML: received ${x.body}")
      x.body.right.exists(x => ! x.startsWith("{") && !x.startsWith("["))
    }
  }

  def getOneWithinTime(url:String, time: LocalDateTime):Future[Boolean] = {
    val bBOutputRequest = BBOutputRequest(number = "1",
      from = Some(time.minusDays(1)), to=Some(time.plusDays(1)),
      outputFormat = Some("JSON")
    )
    GetBBOutput.get(url,bBOutputRequest,flavor).map{ x =>
      Console.println(s"GET: received ${x.body}")
      x.body.right.exists(! _.isEmpty) &&
      x.code == 200
    }
  }



  //def postOneIncorrectIp(url:String):Future[Boolean]


}
