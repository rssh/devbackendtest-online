package devchallenge.other.tester

case class Coordinates(
    longitude: Double,
    latitude: Double
)


object Coordinates {


  // return distance in km.
  def distance( x: Coordinates, y: Coordinates): Double = {
    /*
    val R = 6371
    val dLat = deg2rad(y.latitude - x.latitude)
    val dLon = deg2rad(y.longitude - x.latitude)
    val a =
      Math.sin(dLat/2) * Math.sin(dLat/2) +
        Math.cos(deg2rad(x.latitude)) * Math.cos(deg2rad(y.latitude)) *
          Math.sin(dLon/2) * Math.sin(dLon/2)
    ;
    val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    val d = R * c; // Distance in km
    d
    */
    ???
  }

  def deg2rad(deg:Double): Double =
    (deg * Math.PI)/180.0

}