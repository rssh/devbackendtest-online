package devchallenge.other.tester

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.util.Random

sealed trait RequestFlavor
case object Flavor520154416683 extends RequestFlavor
case object Flavor762141944277 extends RequestFlavor
case object Flavor811125320161 extends RequestFlavor
case object Flavor913136672544 extends RequestFlavor


object ExternalTest {


  val flavor: RequestFlavor = Flavor762141944277

  def main(args: Array[String]):Unit = {
    val baseUrl = if (args.length < 2) {
      val defaultUrl = "http://127.0.0.1:8080/ourell"
      Console.println(s"use default app:  ${defaultUrl}")
      defaultUrl
    } else {
      args(1)
    }
    Console.println(s"baseUrl=${baseUrl}");

    val f1 = postOne(baseUrl)
    val r1 = Await.result(f1,5 seconds)
    System.out.println(s"one test: $r1")

    val f2 = getOne(baseUrl)
    val r2 = Await.result(f2,5 seconds)
    System.out.println(s"one test: $r2")

  }

  val random = new Random()

  import scala.concurrent.ExecutionContext.Implicits.global

  def postOne(url:String):Future[Boolean] = {
    val bBInput = BBInputGenerator.generateInitial(1,random,BBInputGeneratorOptions())
    PostBBInput.post(url,bBInput, flavor).map{x =>
      Console.println(s"POST: received ${x.body}")
      x.code == 200
    }
  }

  def getOne(url:String):Future[Boolean] = {
    val bBOutputRequest = BBOutputRequest(number = "1")
    GetBBOutput.get(url,bBOutputRequest,flavor).map{ x =>
      Console.println(s"GET: received ${x.body}")
      x.code == 200
    }
  }




  //def postOneIncorrectIp(url:String):Future[Boolean]


}
