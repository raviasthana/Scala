package week1

object SqareRootWithNewtonMethod {

  def abs(x: Double) = if (x < 0) -x else x       //> abs: (x: Double)Double

	
  def sqrt(x: Double) = {

    /*
  sqrtIter is an iterative function because the right side of the function
  calls itself. Return time of an iterative funtion needs to be specified
  explicitly. This is because to compute the return type Scala complier needs
  to evaluate the right side of the function, however, as rightside calls
  itself, the compiler will get stuck. So to break this, return type of an
  iterative function needs to be specified explicitly
  */
  //avoid namespace pollution by embedding auxilliary functions
  /*
    def sqrtIter(guess: Double, x: Double): Double =
      if (isGoodEnough(guess, x)) guess
      else sqrtIter(improve(guess, x), x)

    def isGoodEnough(guess: Double, x: Double) =
      abs(guess * guess - x) / x < 0.001

    def improve(guess: Double, x: Double) =
      (guess + x / guess) / 2
    
    sqrtIter(1.0, x)
   */
		//as variable x is visible throughout the function, there is no need to
		//pass it explicitly within each auxilliary function
    def sqrtIter(guess: Double): Double =
      if (isGoodEnough(guess)) guess
      else sqrtIter(improve(guess))

    def isGoodEnough(guess: Double) =
      abs(guess * guess - x) / x < 0.001

    def improve(guess: Double) =
      (guess + x / guess) / 2
    
    sqrtIter(1.0)
  }                                               //> sqrt: (x: Double)Double

  sqrt(2)                                         //> res0: Double = 1.4142156862745097
  sqrt(4)                                         //> res1: Double = 2.000609756097561
  sqrt(1e-6)                                      //> res2: Double = 0.0010000001533016628
  sqrt(1e60)                                      //> res3: Double = 1.0000788456669446E30
}