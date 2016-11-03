package week4

//representation of a pure Boolean as a class
abstract class Boolean {
  
  def ifThenElse[T](thn: => T, els: => T): T //usage: condition.ifThenElse(x,y)
  
  def && (x: => Boolean): Boolean = ifThenElse(x, FALSE)
  def || (x: => Boolean): Boolean = ifThenElse(TRUE, x)
  
  def unary_! : Boolean            = ifThenElse(FALSE, TRUE)
  
  def == (x: Boolean)              = ifThenElse(x, x.unary_!)
  def != (x: Boolean)              = ifThenElse(x.unary_!, x)
  
  def < (that: => Boolean)         = ifThenElse(FALSE, that)
}

object TRUE extends Boolean{
  def ifThenElse[T](thn: => T, els: => T) = thn
  
  override def toString: String = "TRUE"
}

object FALSE extends Boolean{
  def ifThenElse[T](thn: => T, els: => T) = els
  
  override def toString: String = "FALSE"
}