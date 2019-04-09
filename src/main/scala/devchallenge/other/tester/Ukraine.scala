package devchallenge.other.tester


object Ukraine extends InRectangleArea {

  override def minLat: Double = 46.111477
  override def minLng: Double = 22.339779
  override def maxLat: Double = 52.269746
  override def maxLng: Double = 39.831170

    val dots = IndexedSeq(
      Coordinates(48.505041, 22.339779),
      Coordinates(50.820402, 24.126098),
      Coordinates(51.617467, 23.676033),
      Coordinates(51.440602, 30.079120),
      Coordinates(52.075570, 31.813381),
      Coordinates(52.269746, 33.815141),
      Coordinates(50.289777, 35.680680),
      Coordinates(49.529500, 39.831170),
      Coordinates(47.955761, 39.522762),
      Coordinates(47.083457, 37.688253),
      Coordinates(46.505942, 35.497824),
      Coordinates(46.111477, 33.689393)
    )

    object Kyiv extends InRectangleArea {

      val Center = Coordinates(50.45466, 30.5238)

      val minLat = 50.344162
      val maxLat = 50.550134
      val minLng = 30.301189
      val maxLng = 30.721654

    }

    object Lviv extends InRectangleArea {


      //distance(
      //  49.840933, 24.013632,
      //  49.852921, 23.987560
      //  49.874879, 23.913702
      //)  approx 8.


      val dots = Seq(
        Coordinates(49.869058, 24.014110),
        Coordinates(49.867297, 24.054906),
        Coordinates(49.856256, 24.079483),
        Coordinates(49.810425, 24.077049),
        Coordinates(49.801175, 23.996701),
        Coordinates(49.823999, 23.953525)
      )

      override def minLat: Double = 49.801175

      override def minLng: Double = 23.953525

      override def maxLat: Double = 49.869058

      override def maxLng: Double = 24.079483
    }


}
