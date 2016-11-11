package week5

object ListOperations {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def removeAt[T](n: Int, xs: List[T]) = {
  	def allButNth(index: Int, list: List[T], acc: List[T]) : List[T] = {
  		if(n > xs.length) xs
  		else
  	  list match {
  	    case Nil => acc
  	    case y :: ys =>
  	      if(index == n) allButNth(index + 1, ys, acc)
  	      else allButNth(index + 1, ys, acc ::: List(y))
  	  }
  	}
  	allButNth(0,xs,Nil)
  }                                               //> removeAt: [T](n: Int, xs: List[T])List[T]
  
  def removeAtBetter[T](n: Int, xs: List[T]) = xs.take(n) ::: xs.drop(n+1)
                                                  //> removeAtBetter: [T](n: Int, xs: List[T])List[T]
  
  removeAt(2, List(0,1,2,3,4,5))                  //> res0: List[Int] = List(0, 1, 3, 4, 5)
  removeAtBetter(2, List(0,1,2,3,4,5))            //> res1: List[Int] = List(0, 1, 3, 4, 5)
  
  List(0, 1, 3, 4, 5) splitAt(2)                  //> res2: (List[Int], List[Int]) = (List(0, 1),List(3, 4, 5))
  
  val pair = ("answer",42)                        //> pair  : (String, Int) = (answer,42)
  val (label, value) = pair                       //> label  : String = answer
                                                  //| value  : Int = 42
  
  val l = pair._1                                 //> l  : String = answer
  val v = pair._2                                 //> v  : Int = 42
  
  def merge(xs: List[Int], ys: List[Int]): List[Int] = {
  	(xs, ys) match {
  		case (Nil, Nil) => Nil
  		case (Nil, ys) => ys
  		case (ys, Nil) => xs
  		case (x :: xs1, y :: ys1) =>
  			if(x < y) x :: merge(xs1, ys)
  			else y :: merge(xs, ys1)
  	}
  }                                               //> merge: (xs: List[Int], ys: List[Int])List[Int]
  
  merge(List(3,6,7),List(1,2,5))                  //> res3: List[Int] = List(1, 2, 3, 5, 6, 7)
  
  def msort(xs: List[Int]): List[Int] = {
    val n = xs.length/2
    if(n == 0) xs
    else{
			val (l1, l2) = xs splitAt n
			merge(msort(l1),msort(l2))
    }
  }                                               //> msort: (xs: List[Int])List[Int]
  
  msort(List(1,4, -3, 2,3))                       //> res4: List[Int] = List(-3, 1, 2, 3, 4)
}