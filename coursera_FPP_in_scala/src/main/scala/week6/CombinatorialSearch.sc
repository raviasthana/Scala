package week6

object CombinatorialSearch {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
    
 	def isPrime(n: Int): Boolean =
		(2 until n) forall (d => n % d != 0)
                                                  //> isPrime: (n: Int)Boolean
	isPrime(3)                                //> res0: Boolean = true
	isPrime(13)                               //> res1: Boolean = true
	isPrime(4)                                //> res2: Boolean = false
	
	/*
  * Higher-order functions such as map, flatMap or filter provide powerful constructs
  * for maintaining lists
  *
  *	But sometimes level of abstraction required by these function make the program
  * difficutl to understand. In this case, Scala's for expression notation can help
  */
		
  val n = 7                                       //> n  : Int = 7
  (1 until n) flatMap (i =>
  	(1 until i) map (j => (i,j))) filter (pair =>
  		isPrime(pair._1 + pair._2))       //> res3: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,2
                                                  //| ), (4,1), (4,3), (5,2), (6,1), (6,5))
  //better version of above program using for expression
	for {
	  i <- 1 until n
	  j <- 1 until i
	  if isPrime(i + j)
	} yield (i,j)                             //> res4: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,2
                                                  //| ), (4,1), (4,3), (5,2), (6,1), (6,5))
	def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
	  (for((x,y) <- xs zip ys) yield x * y).sum
                                                  //> scalarProduct: (xs: Vector[Double], ys: Vector[Double])Double
}