package devchallenge.other.tester

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.util.Random


object ExternalTest {

  def main(args: Array[String]):Unit = {
    /*
    val baseUrl = if (args.length < 2) {
      val defaultUrl = "http://127.0.0.1:8080/ourel"
      Console.println(s"use default app:  ${defaultUrl}")
      defaultUrl
    } else {
      args(1)
    }
    Console.println(s"baseUrl=${baseUrl}");

    val f1 = postOne(baseUrl)

    val r = Await.result(f1,5 seconds)
    System.out.println("one test: $r")
    */
  }

  val random = new Random()

  import scala.concurrent.ExecutionContext.Implicits.global

  def postOne(url:String):Future[Boolean] = {
    ???
    /*
    val bBInput = BBInputGenerator.generateInitial(1,random,BBInputGeneratorOptions())
    PostBBInput(url,bBInput).map{x =>
      Console.println(s"received ${x.body}")
      x.code == 200
    }
    */
  }



}
