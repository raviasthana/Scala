package week2

object rationals {
  val x = new Rational(1,3)                       //> x  : week2.Rational = 1/3
  val y = new Rational(5,7)                       //> y  : week2.Rational = 5/7
  val z = new Rational(3,2)                       //> z  : week2.Rational = 3/2
  x.numer                                         //> res0: Int = 1
  x.denom                                         //> res1: Int = 3
  //x.add(y)
  x + y                                           //> res2: week2.Rational = 22/21
  //x.sub(y).sub(z)
  x - y - z                                       //> res3: week2.Rational = -79/42
  //y.add(y)
  y + y                                           //> res4: week2.Rational = 10/7
  
  x < y                                           //> res5: Boolean = true
  
  x max y                                         //> res6: week2.Rational = 5/7
  z.max(y)                                        //> res7: week2.Rational = 3/2
  
  //val strange = new Rational(1,0)
  //strange.add(strange)
}

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