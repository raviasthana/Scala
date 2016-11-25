package week4

//Functional decomposition using pattern matching
object PatternMatching {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def show(e: Expression): String = e match {
    case NumberExpr(n) => n.toString()
    case SumExpr(e1,e2) => show(e1) + " + " + show(e2)
  }                                               //> show: (e: week4.Expression)String
  
  show(NumberExpr(2))                             //> res0: String = 2
  show(SumExpr(NumberExpr(2),NumberExpr(5)))      //> res1: String = 2 + 5
}

trait Expression {
}

case class NumberExpr(n: Int) extends Expression {
}

case class SumExpr(e1: Expression, e2: Expression) extends Expression {
}

case class ProdExpr(e1: Expression, e2: Expression) extends Expression {
}