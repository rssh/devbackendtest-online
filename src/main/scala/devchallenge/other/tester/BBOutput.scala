package devchallenge.other.tester

import java.time.LocalDateTime


case class BBOutputRequest(
    number: String,
    from: Option[LocalDateTime] = None,
    to: Option[LocalDateTime] = None,
    outputFormat: Option[String] = None
)
