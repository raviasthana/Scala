package week1

object TailRecursiveFactorial {
  
	def factorial(n: Int): Int = {
	  /* non-tail recursive factorial
		if(n == 0) 1 else n * factorial(n - 1)
		*/
		def loop(acc: Int, n: Int): Int = {
		  if(n == 0) acc
		  else loop(acc * n, n-1)
		}
		loop(1,n)
	}                                         //> factorial: (n: Int)Int
	
	factorial(5)                              //> res0: Int = 120
	
}