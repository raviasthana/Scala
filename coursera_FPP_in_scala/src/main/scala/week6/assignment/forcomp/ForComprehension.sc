package week6.assignment.forcomp

case class Postcard(msg: String)

object ForComprehension {

  def sendPostcards: List[Postcard] = {
    val states = List("Arizona", "New Mexico",
      "Texas", "Louisiana",
      "Mississippi", "Virginia",
      "New York", "Ohio",
      "Illinois")
    val relatives = List("Grandma", "Grandpa", "Aunt Dottie", "Dad")
    val travellers = List("Kelsey", "DJ")

    var postcardList: List[Postcard] = List()

    for (h <- 0 until travellers.length) {
      val sender = travellers(h)

      for (i <- 0 until relatives.length) {
        val recipient = relatives(i)

        for (j <- 0 until states.length) {
          val theState = states(j)
          postcardList ::=
            new Postcard("Dear " + recipient + ", " +
              "Wish you were here in " +
              theState + "! " +
              "Love, " + sender)
        }
      }
    }

    postcardList
  }

  def sendPostcards3: List[Postcard] = {
    val states = List("Arizona", "New Mexico",
      "Texas", "Louisiana",
      "Mississippi", "Virginia",
      "New York", "Ohio",
      "Illinois")
    val relatives = List("Grandma", "Grandpa", "Aunt Dottie", "Dad")
    val travellers = List("Kelsey", "DJ")

    for {
      traveller <- travellers
      sender = traveller + " (your favorite)"
      relative <- relatives
      theState <- states
      if (relative.startsWith("G"))
    } yield {
      new Postcard("Dear " + relative + ", " +
        "Wish you were here in " +
        theState + "! " +
        "Love, " + sender)
    }
  }
  
  sendPostcards mkString "\n"
  //sendPostcards3 mkString "\n"
}