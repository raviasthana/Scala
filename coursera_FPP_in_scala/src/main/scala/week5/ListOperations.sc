package week5

object ListOperations {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def removeAt[T](n: Int, xs: List[T]) = {
    def allButNth(index: Int, list: List[T], acc: List[T]): List[T] = {
      if (n > xs.length) xs
      else
        list match {
          case Nil => acc
          case y :: ys =>
            if (index == n) allButNth(index + 1, ys, acc)
            else allButNth(index + 1, ys, acc ::: List(y))
        }
    }
    allButNth(0, xs, Nil)
  }                                               //> removeAt: [T](n: Int, xs: List[T])List[T]

  def removeAtBetter[T](n: Int, xs: List[T]) = xs.take(n) ::: xs.drop(n + 1)
                                                  //> removeAtBetter: [T](n: Int, xs: List[T])List[T]

  removeAt(2, List(0, 1, 2, 3, 4, 5))             //> res0: List[Int] = List(0, 1, 3, 4, 5)
  removeAtBetter(2, List(0, 1, 2, 3, 4, 5))       //> res1: List[Int] = List(0, 1, 3, 4, 5)

  List(0, 1, 3, 4, 5) splitAt (2)                 //> res2: (List[Int], List[Int]) = (List(0, 1),List(3, 4, 5))

  val pair = ("answer", 42)                       //> pair  : (String, Int) = (answer,42)
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
        if (x < y) x :: merge(xs1, ys)
        else y :: merge(xs, ys1)
    }
  }                                               //> merge: (xs: List[Int], ys: List[Int])List[Int]

  merge(List(3, 6, 7), List(1, 2, 5))             //> res3: List[Int] = List(1, 2, 3, 5, 6, 7)

  def msort(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (l1, l2) = xs splitAt n
      merge(msort(l1), msort(l2))
    }
  }                                               //> msort: (xs: List[Int])List[Int]

  msort(List(1, 4, -3, 2, 3))                     //> res4: List[Int] = List(-3, 1, 2, 3, 4)

  def msort2[T](list: List[T])(lt: (T, T) => Boolean): List[T] = {
    val n = list.length / 2
    if (n == 0) list
    else {
      def merge2(xs: List[T], ys: List[T]): List[T] = {
        (xs, ys) match {
          case (Nil, Nil) => Nil
          case (Nil, ys) => ys
          case (ys, Nil) => xs
          case (x :: xs1, y :: ys1) =>
            if (lt(x, y)) x :: merge2(xs1, ys)
            else y :: merge2(xs, ys1)
        }
      }
      val (l1, l2) = list splitAt n
      merge2(msort2(l1)(lt), msort2(l2)(lt))
    }
  }                                               //> msort2: [T](list: List[T])(lt: (T, T) => Boolean)List[T]

  //msort2(List(1,4, -3, 2,3))((x: Int, y: Int) => x < y)
  msort2(List(1, 4, -3, 2, 3))((x, y) => x < y)   //> res5: List[Int] = List(-3, 1, 2, 3, 4)

  val fruits = List("apple", "pineapple", "orange", "banana")
                                                  //> fruits  : List[String] = List(apple, pineapple, orange, banana)
  msort2(fruits)((x, y) => x.compareTo(y) < 0)    //> res6: List[String] = List(apple, banana, orange, pineapple)

  def msort3[T](list: List[T])(ord: Ordering[T]): List[T] = {
    val n = list.length / 2
    if (n == 0) list
    else {
      def merge3(xs: List[T], ys: List[T]): List[T] = {
        (xs, ys) match {
          case (Nil, Nil) => Nil
          case (Nil, ys) => ys
          case (ys, Nil) => xs
          case (x :: xs1, y :: ys1) =>
            if (ord.lt(x, y)) x :: merge3(xs1, ys)
            else y :: merge3(xs, ys1)
        }
      }
      val (l1, l2) = list splitAt n
      merge3(msort3(l1)(ord), msort3(l2)(ord))
    }
  }                                               //> msort3: [T](list: List[T])(ord: Ordering[T])List[T]

  msort3(List(1, 4, -3, 2, 3))(Ordering.Int)      //> res7: List[Int] = List(-3, 1, 2, 3, 4)

  def msort4[T](list: List[T])(implicit ord: Ordering[T]): List[T] = { //declaring ordering as implicit which
    val n = list.length / 2 //results in compiler synthesizing and
    if (n == 0) list //providing one implicitly
    else {
      def merge4(xs: List[T], ys: List[T]): List[T] = {
        (xs, ys) match {
          case (Nil, Nil) => Nil
          case (Nil, ys) => ys
          case (ys, Nil) => xs
          case (x :: xs1, y :: ys1) =>
            if (ord.lt(x, y)) x :: merge4(xs1, ys)
            else y :: merge4(xs, ys1)
        }
      }
      val (l1, l2) = list splitAt n
      merge4(msort4(l1)(ord), msort4(l2)(ord))
    }
  }                                               //> msort4: [T](list: List[T])(implicit ord: Ordering[T])List[T]
  msort4(List(1, 4, -3, 7, 2, 3))                 //> res8: List[Int] = List(-3, 1, 2, 3, 4, 7)

  def squareList1(xs: List[Int]): List[Int] =
    xs match {
      case Nil => Nil
      case y :: ys => (y * y) :: squareList1(ys)
    }                                             //> squareList1: (xs: List[Int])List[Int]

  def squareList2(xs: List[Int]): List[Int] =
    xs map (x => (x * x))                         //> squareList2: (xs: List[Int])List[Int]
    
  squareList1(List(2,3,4))                        //> res9: List[Int] = List(4, 9, 16)
	squareList2(List(2,3,4))                  //> res10: List[Int] = List(4, 9, 16)
	  
}