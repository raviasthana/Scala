package week3

class Rational(x: Int, y: Int) {
  
  require(y !=0, "Denominator must be non-zero")
  
  //this is used in function position to declare
  //a new constructor
  def this(x: Int) = this(x,1)
  
  private def gcd(a: Int, b: Int): Int = if(b == 0) a else gcd(b, a % b)
  private val g = gcd(x,y)
  def numer = x / g
  def denom = y / g
  //def numer = x
  //def denom = y
  
  //def less(that: Rational) = numer * that.denom < that.numer * denom
  //relaxed identifier in scala allows to use < symbol in place of name 'less'
  def < (that: Rational) = numer * that.denom < that.numer * denom
  
  //def max(that: Rational) = if(this.less(that)) that else this
  def max(that: Rational) = if(this < that) that else this
  
  //def add(that: Rational) =
  //scala realxed identifier usage
  def + (that: Rational) =
  	new Rational(numer * that.denom +  that.numer * denom,
  	denom * that.denom)
  
  //def neg: Rational = new Rational(-numer,denom)
  def unary_- : Rational = new Rational(-numer,denom)
  
  //def sub(that: Rational) = add(that.neg)
  //scala realxed identifier usage
  //def - (that: Rational) = this + that.neg
  def - (that: Rational) = this + -that
  	
  override def toString = {
    //this may result into Arithmatic overflow as numer and denom are integers
    //So the number normalisation should happen as early as possible so that
    //we can perform more operations - Which is what is done above using
    //greatest common divisor private val g and then normalizing x and y using g
    //val g = gcd(numer,denom)
    //numer/g + "/" + denom/g
    numer + "/" + denom
  }
}