package week6

object polynomials {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  class Poly(val terms0: Map[Int, Double]) {
  	def this(bindings: (Int, Double)*) = this(bindings.toMap) //sequence of bindings represented by *
  			//this mean we can pass arbitrary number of repeated arguments to construct Poly
  	val terms = terms0 withDefaultValue 0.0
  	//def + (other: Poly) = new Poly(terms ++ (other.terms map adjust))
  	def + (other: Poly) = new Poly((other.terms foldLeft terms)(addTerm)) //version with foldLeft is more efficient
  	def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
  		val (exp, coeff) = term
  		terms + (exp -> (coeff + terms(exp)))
  	}
  	def adjust(term: (Int, Double)): (Int, Double) = {
  		val (exp, coeff) = term
  		/* //case matching not needed with default value in place
  		terms get exp match {
  			case Some(coeff1) => exp -> (coeff + coeff1)
  			case None => exp -> coeff
  		}*/
  		exp -> (coeff + terms(exp)) //better way to express above match expression
  	}
  	override def toString =
  		(for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff+"x^"+exp) mkString " + "
  }
  
  //val p1 = new Poly(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
  val p1 = new Poly(1 -> 2.0, 3 -> 4.0, 5 -> 6.2) //> p1  : week6.polynomials.Poly = 6.2x^5 + 4.0x^3 + 2.0x^1
  //val p2 = new Poly(Map(0 -> 3.0, 3 -> 7.0))
  val p2 = new Poly(0 -> 3.0, 3 -> 7.0)           //> p2  : week6.polynomials.Poly = 7.0x^3 + 3.0x^0
  
  p1 + p2                                         //> res0: week6.polynomials.Poly = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
  p1.terms(7)                                     //> res1: Double = 0.0
}