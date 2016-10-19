package week2

object FixedPoint {
  
  val tolerance = .0001                           //> tolerance  : Double = 1.0E-4
  
  def isCloseEnough(x: Double, y: Double): Boolean =
    (((x - y)/x)/x) < tolerance                   //> isCloseEnough: (x: Double, y: Double)Boolean
    
  def fixedPoint(f: Double => Double)(x: Double): Double = {
    0
  }                                               //> fixedPoint: (f: Double => Double)(x: Double)Double
}