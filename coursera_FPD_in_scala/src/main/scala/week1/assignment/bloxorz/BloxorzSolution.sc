package week1.assignment.bloxorz

object BloxorzSolution {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
    val level =
    """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin                  //> level  : String = ooo-------
                                                  //| oSoooo----
                                                  //| ooooooooo-
                                                  //| -ooooooooo
                                                  //| -----ooToo
                                                  //| ------ooo-
      
  val terrain = Vector(level.split("\n").map(str => Vector(str: _*)): _*)
                                                  //> terrain  : scala.collection.immutable.Vector[scala.collection.immutable.Vect
                                                  //| or[Char]] = Vector(Vector(o, o, o, -, -, -, -, -, -, -), Vector(o, S, o, o, 
                                                  //| o, o, -, -, -, -), Vector(o, o, o, o, o, o, o, o, o, -), Vector(-, o, o, o, 
                                                  //| o, o, o, o, o, o), Vector(-, -, -, -, -, o, o, T, o, o), Vector(-, -, -, -, 
                                                  //| -, -, o, o, o, -))
  val v = Vector(Vector(1, 2, 3), Vector(6, 5, 4), Vector(7, 8, 9))
                                                  //> v  : scala.collection.immutable.Vector[scala.collection.immutable.Vector[Int
                                                  //| ]] = Vector(Vector(1, 2, 3), Vector(6, 5, 4), Vector(7, 8, 9))
  val num = 4                                     //> num  : Int = 4
  v.map(_ indexOf num)                            //> res0: scala.collection.immutable.Vector[Int] = Vector(-1, 2, -1)
  v.map(_ indexOf num).zipWithIndex               //> res1: scala.collection.immutable.Vector[(Int, Int)] = Vector((-1,0), (2,1), 
                                                  //| (-1,2))
  v.map(_ indexOf num).zipWithIndex.find(_._1 > -1)
                                                  //> res2: Option[(Int, Int)] = Some((2,1))
 	val row = v.indexWhere(_.indexOf(num) > -1)
                                                  //> row  : Int = 1
  val col = v(row).indexOf(num)                   //> col  : Int = 2
  
  val lst = List((2,'a'),(3,'b'),(5,'c'),(8,'d'),(10,'e'))
                                                  //> lst  : List[(Int, Char)] = List((2,a), (3,b), (5,c), (8,d), (10,e))
  lst filter(p => p._1 % 2 == 0)
  
}