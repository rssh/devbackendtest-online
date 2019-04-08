package devchallenge.other.tester

import java.time.LocalDateTime


case class BBInput(
    number: String,
    imei: String,
    ip: Option[String],
    timestamp: Option[LocalDateTime],
    coordinates: Coordinates
)
