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
  
  def nth[T](n: Int, list: List[T]): T = {
    def loop(index: Int, tList: List[T]): T = {
      if(tList.isEmpty) throw new IndexOutOfBoundsException("Index value [" + index + "] is out of bound")
      else if(index == n) tList.head
      else loop(index + 1, tList.tail)
    }
    if(n < 0) throw new IndexOutOfBoundsException("Index value [" + n + "] is out of bound")
    else loop(0,list)
  }                                               //> nth: [T](n: Int, list: week3.List[T])T
  
  def nthBetter[T](n: Int, list: List[T]): T = {
    if(list.isEmpty) throw new IndexOutOfBoundsException
    else if(n == 0) list.head
    else nthBetter(n-1, list.tail)
  }                                               //> nthBetter: [T](n: Int, list: week3.List[T])T
  
  val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
                                                  //> list  : week3.Cons[Int] = week3.Cons@5b464ce8
  
  nth(2,list)                                     //> res2: Int = 3
  nthBetter(1,list)                               //> res3: Int = 2
}