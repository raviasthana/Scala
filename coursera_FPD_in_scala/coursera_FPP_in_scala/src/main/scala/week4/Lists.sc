package week4


object Lists {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  /**
  * There are two important differences between List and Arrays in scala world
  * 1) List are immutable - the elements of a list cannot be changed
  * 2) List are recursive while arrays are flat
  */
  
  val l = scala.List("apples","oranges","pears")  //> l  : List[String] = List(apples, oranges, pears)
  val l2 = "apples" :: ("oranges" :: ("pears" :: scala.Nil))
  
}