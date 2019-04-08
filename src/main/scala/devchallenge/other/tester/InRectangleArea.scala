package devchallenge.other.tester

trait InRectangleArea {

   def minLat: Double
   def minLng: Double
   def maxLat: Double
   def maxLng: Double

   def diameterKm(): Double = {
      Coordinates.distance(Coordinates(minLat,minLng),Coordinates(maxLat,maxLng))
   }

   def stepKm():(Double,Double) = {
      val d = diameterKm()
      val dx = (maxLat-minLat)/d
      val dy = (maxLng-minLng)/d
      (dx,dy)
   }

}
