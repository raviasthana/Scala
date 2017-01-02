package week1.assignment.scalashop

object test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val width = 20                                  //> width  : Int = 20
  val numTasks = 10                               //> numTasks  : Int = 10
  val separateStrips = 0 to width by (width/numTasks max 1)
                                                  //> separateStrips  : scala.collection.immutable.Range = Range(0, 2, 4, 6, 8, 10
                                                  //| , 12, 14, 16, 18, 20)
  separateStrips.tail                             //> res0: scala.collection.immutable.Range = Range(2, 4, 6, 8, 10, 12, 14, 16, 1
                                                  //| 8, 20)
	separateStrips.zip(separateStrips.tail)   //> res1: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((0,2), (2,4
                                                  //| ), (4,6), (6,8), (8,10), (10,12), (12,14), (14,16), (16,18), (18,20))
}