package week6

	/*
	*	Sets are unordred - elements of a set do not have a
	* predefined order in which they appear in the set
	*
	* Sets do not allow duplicate elements
	*
	* Fundamental method operation on sets is "contains"
	*
	*/
object nqueens {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
	def queens(n: Int): Set[List[Int]] = {
		def placeQueens(k: Int): Set[List[Int]] = {
		  if(k == 0) Set(List())
		  else
		  	for {
		  		queens <- placeQueens(k - 1)
		  		col <- 0 until n
		  		if isSafe(col, queens)
		  	}	yield col :: queens
		}
		placeQueens(n)
	}                                         //> queens: (n: Int)Set[List[Int]]
	
	def isSafe(col: Int, queens: List[Int]): Boolean = {
		//add rows to all the queens
		val row = queens.length
		val queensWithRow = (row - 1 to 0 by -1) zip queens
		queensWithRow forall {
			case (r,c) => col != c && math.abs(col - c) != row - r
		}
	}                                         //> isSafe: (col: Int, queens: List[Int])Boolean
	
	def show(queens: List[Int]) = {
		val lines =
			for (col <- queens.reverse) // reverse because in the solution last column is first
			yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
		"\n" + (lines mkString "\n") //mkString takes a collection and prints
																//all the elements in the collection - it can take second argument
																//as well to append in each element in collection before printing
	}                                         //> show: (queens: List[Int])String
	
	//(queens(4) map show) mkString "\n"
	//(queens(8) map show) mkString "\n"
	(queens(8) take 3 map show) mkString "\n" //> res0: String = "
                                                  //| * * * * * X * * 
                                                  //| * * * X * * * * 
                                                  //| * X * * * * * * 
                                                  //| * * * * * * * X 
                                                  //| * * * * X * * * 
                                                  //| * * * * * * X * 
                                                  //| X * * * * * * * 
                                                  //| * * X * * * * * 
                                                  //| 
                                                  //| * * * * X * * * 
                                                  //| * * * * * * X * 
                                                  //| * X * * * * * * 
                                                  //| * * * X * * * * 
                                                  //| * * * * * * * X 
                                                  //| X * * * * * * * 
                                                  //| * * X * * * * * 
                                                  //| * * * * * X * * 
                                                  //| 
                                                  //| * * * * * X * * 
                                                  //| * * X * * * * * 
                                                  //| * * * * * * X * 
                                                  //| * * * X * * * * 
                                                  //| X * * * * * * * 
                                                  //| * * * * * * * X 
                                                  //| * X * * * * * * 
                                                  //| * * * * X * * * "
}