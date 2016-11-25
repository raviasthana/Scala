package week3

//import week3._ //import everything from week3 - called wildcard import
//import week3.{Rational,Hello} //just another style of import - called named import

object scratch {
  new Rational(1,2)                               //> res0: week3.Rational = 1/2
  
  def error(msg: String) = throw new Error(msg)   //> error: (msg: String)Nothing
  //error("test")
  
  val x = null                                    //> x  : Null = null
  val y: String = x                               //> y  : String = null
  //val z: Int = null - null can't be used for AnyVal type only AnyRef type
  
  if (true) 1 else false                          //> res1: AnyVal = 1
}