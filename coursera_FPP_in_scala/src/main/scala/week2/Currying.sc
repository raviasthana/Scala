package week2

/**
* This worksheet shows Scala syntactic sugar called currying
*/
object Currying {
  
  def sum(f:Int => Int): (Int,Int) => Int = {
  	def sumF(a: Int, b: Int): Int = {
			if(a > b) 0
			else f(a) + sumF(a+1,b)
  	}
  	sumF
  }                                               //> sum: (f: Int => Int)(Int, Int) => Int
  
  def sumInts = sum(x => x)                       //> sumInts: => (Int, Int) => Int
  def sumCubes = sum(x => x * x * x)              //> sumCubes: => (Int, Int) => Int
  
  sumInts(1,4) + sumCubes(1,4)                    //> res0: Int = 110
  //directly applying funtion sum(cube)(a,b)
  sum(x => x * x * x)(1,4)                        //> res1: Int = 100
  
  /* The definition of function that returns function is so useful in
  *  functional programming that there is special syntax for it in Scala
  *
  *  sum2 definition is equivalent to sum definition above but much shorter
  */
  def sum2(f: Int => Int)(a: Int, b: Int): Int = {
    if(a > b) 0 else f(a) + sum(f)(a+1,b)
  }                                               //> sum2: (f: Int => Int)(a: Int, b: Int)Int
  sum2(x => x * x)(3,5)                           //> res2: Int = 50
}