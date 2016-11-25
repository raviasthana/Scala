package week4

object Decomposition {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def eval(e: Expr): Int = {
    if(e.isNumber || e.isVar) e.numValue
    else if(e.isSum) eval(e.leftOp) + eval(e.rightOp)
    else if(e.isProd) eval(e.leftOp) * eval(e.rightOp)
    else throw new Error("unknown Expression")
  }                                               //> eval: (e: week4.Expr)Int
    
  eval(new Sum(new Number(1),new Number(2)))      //> res0: Int = 3
  eval(new Prod(new Number(1),new Number(2)))     //> res1: Int = 2
  eval(new Prod(new Sum(new Number(1),new Number(2)),new Sum(new Number(3), new Number(4)) ))
                                                  //> res2: Int = 21
  eval(new Sum(new Var("5"),new Number(2)))       //> res3: Int = 7
  eval(new Sum(new Var("abc"),new Number(2)))     //> res4: Int = 2
  
  /*
  * following is the implementation of eval method without classificaiton methods
  * + No need for classification methods, access methods only for the class where
  * the value is defined
  * - low level and potentially unsafe
  */
  def evalWithoutClassificationMethod(e: Expr): Int = {
    if(e.isInstanceOf[Number])
      e.asInstanceOf[Number].numValue
    else if(e.isInstanceOf[Sum])
      evalWithoutClassificationMethod(e.asInstanceOf[Sum].leftOp) +
      evalWithoutClassificationMethod(e.asInstanceOf[Sum].rightOp)
    else if(e.isInstanceOf[Prod])
      evalWithoutClassificationMethod(e.asInstanceOf[Prod].leftOp) *
      evalWithoutClassificationMethod(e.asInstanceOf[Prod].rightOp)
    else throw new Error("Unknown Expression")
  }                                               //> evalWithoutClassificationMethod: (e: week4.Expr)Int
  
  evalWithoutClassificationMethod(new Sum(new Number(1),new Number(2)))
                                                  //> res5: Int = 3
  evalWithoutClassificationMethod(new Prod(new Sum(new Number(1),new Number(2)),new Sum(new Number(3), new Number(4)) ))
                                                  //> res6: Int = 21
                                                  
	new Sum(new Number(1),new Number(2)).betterEval
                                                  //> res7: Int = 3
}

trait Expr {
	//classification methods
  def isNumber: scala.Boolean
  def isSum: scala.Boolean
  
  def isProd: scala.Boolean
  def isVar: scala.Boolean
  
  //accessor methods
  def numValue: Int
  def leftOp: Expr
  def rightOp: Expr
  
  def betterEval: Int //OO decomposition
}

class Number(n: Int) extends Expr {
  def isNumber = true
  def isSum = false
  
  def isProd = false
  def isVar = false
  
  def numValue = n
  def leftOp = throw new Error("Number.leftOf")
  def rightOp = throw new Error("Number.rightOp")
  
  def betterEval = n //OO decomposition
}

class Sum(e1: Expr, e2: Expr) extends Expr {
  def isNumber = false
  def isSum = true
  
  def isProd = false
  def isVar = false
  
  def numValue = throw new Error("Sum.numValue")
  def leftOp = e1
  def rightOp = e2
  
  def betterEval = e1.betterEval + e2.betterEval
}

class Prod(e1: Expr, e2: Expr) extends Expr { // e1 * e2
  def isNumber = false
  def isSum = false
  
  def isProd = true
  def isVar = false
  
  def numValue = throw new Error("Prod.numValue")
  def leftOp = e1
  def rightOp = e2
  
  def betterEval = e1.betterEval * e2.betterEval
}

class Var(x: String) extends Expr {
  def isNumber = false
  def isSum = false
  
  def isProd = false
  def isVar = true
  
  //def numValue = x.toInt //this ofcourse can result in NumberFormatException
  def numValue = toInt(x).getOrElse(0)
  
  def toInt(s: String): Option[Int] = {
    try{
      Some(s.toInt)
    }catch{
      case e: Exception => None
    }
  }

  def leftOp = throw new Error("Var.leftOf")
  def rightOp = throw new Error("Var.rightOp")
  
  def betterEval = numValue
}