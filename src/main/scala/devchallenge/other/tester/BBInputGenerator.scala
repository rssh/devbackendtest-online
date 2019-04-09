package devchallenge.other.tester

import java.time.LocalDateTime

import scala.util.Random

case class BBInputGeneratorOptions(
    ipPrefix: String = "10.10.",
    startTime: LocalDateTime = LocalDateTime.now(),
    rectangleArea: InRectangleArea = Ukraine,
    speed: Double = 2.5 ,
    frequencyPerHour: Int = 60,
    minDistances: Map[Int,Int] = Map.empty
)

object BBInputGenerator {



    def  generateTraces(
        nPhones:Int,
        nPoints:Int,
        entropy: Random,
        options: BBInputGeneratorOptions
        ): Seq[BBInput] = {

      val traces = (1 to nPhones) map (x => Seq(generateInitial(x,entropy,options)) )



       ???
    }


    def generateInitial(n:Int, entropy: Random, options:BBInputGeneratorOptions): BBInput = {
        import options.rectangleArea._
        BBInput(
            number = s"${n}",
            imei = s"imei-${n}",
            ip = Some(nToIp4(n,options)),
            timestamp = Some(options.startTime),
            coordinates = Coordinates(
                minLat + entropy.nextDouble()*(maxLat - minLat),
                minLng + entropy.nextDouble()*(maxLng - minLng)
            )
        )
    }

    def generateNext(n:Int, entropy: Random, step: Int, prev: BBInput, options: BBInputGeneratorOptions): BBInput = {
      val near = options.minDistances.filter(_._1 == n)
      val (kmX,kmY) = options.rectangleArea.stepKm()
      val rx = (entropy.nextDouble() - 0.5)*2
      var dx = rx*(kmX * options.speed)/options.frequencyPerHour
      val ry = (entropy.nextDouble() - 0.5)*2
      val dy = ry*(kmY * options.speed)/options.frequencyPerHour
      var nx = prev.coordinates.longitude + dx
      if (nx > options.rectangleArea.maxLat || nx < options.rectangleArea.minLat) {
        nx = prev.coordinates.longitude
      }
      var ny = prev.coordinates.latitude
      if (ny > options.rectangleArea.maxLng || ny < options.rectangleArea.minLng) {
        ny = prev.coordinates.latitude
      }
      val nSeconds = (60*60)/options.frequencyPerHour
      prev.copy(
        timestamp = prev.timestamp.map(_.plusSeconds(nSeconds)),
        coordinates = Coordinates(nx,ny)
      )
    }



    private def nToIp4(n:Int, options:BBInputGeneratorOptions):String = {
        val b3 = (n >> 8) & 0xFF
        val b4 = (n & 0xFF)
        s"${options.ipPrefix}${b3}.${b4}"
    }


}
