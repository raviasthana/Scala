package week1

object GreatestCommonDivisor {
  def gcd(a: Int, b: Int): Int = {
  	if(b == 0) a else gcd(b, a % b)
  }                                               //> gcd: (a: Int, b: Int)Int
  
  gcd(14,21)                                      //> res0: Int = 7
}