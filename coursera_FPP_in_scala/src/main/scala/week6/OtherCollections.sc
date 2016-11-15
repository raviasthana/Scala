package week6

object OtherCollections {
  println("Welcome to the Scala worksheet")
  
  val xs = Array(1,2,3,44)
  xs map (x => x * 2)
  
  val s = "Hello World"
  s filter (c => c.isUpper)
  
  val r: Range = 1 until 5
	
	s exists (c => c.isUpper)
	s forall (c => c.isUpper)
	
	val pairs = List(1,2,3) zip s
	pairs.unzip
	
	s flatMap (c => List('.',c))
	
	xs.sum
	xs.product
	
	//to list all combinations of numbers x and y to where x is drawn from
	//1..M and y is drawn from 1..N
	//(1 to M) flatMap (x => (1 to N) map (y => (x,y)))
	
	
	//Scalar product	- to compute scalar product of two vectors
	//scalar product of two vectors is the sum of the product of corresponding elements
	def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
		(xs zip ys).map(xy => xy._1 * xy._2).sum //or
		//(xs zip ys).map(case (x,y) => x * y).sum
}