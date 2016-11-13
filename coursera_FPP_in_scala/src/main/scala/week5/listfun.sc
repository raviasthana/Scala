package week5

object listfun {

  val nos = List(1, 4, -3, 2, 3)                  //> nos  : List[Int] = List(1, 4, -3, 2, 3)
  val fruits = List("apple", "pineapple", "orange", "banana")
                                                  //> fruits  : List[String] = List(apple, pineapple, orange, banana)

  nos filter (x => x > 0)                         //> res0: List[Int] = List(1, 4, 2, 3)
  nos filterNot (x => x > 0)                      //> res1: List[Int] = List(-3)
  nos partition { x => x > 0 }                    //> res2: (List[Int], List[Int]) = (List(1, 4, 2, 3),List(-3))

  nos takeWhile { x => x > 0 }                    //> res3: List[Int] = List(1, 4)
  nos dropWhile { x => x > 0 }                    //> res4: List[Int] = List(-3, 2, 3)
  nos span { x => x > 0 }                         //> res5: (List[Int], List[Int]) = (List(1, 4),List(-3, 2, 3))

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      val (first, rest) = xs span { y => y == x }
      first :: pack(rest)
  }                                               //> pack: [T](xs: List[T])List[List[T]]

  val data = List("a", "a", "a", "b", "c", "c", "a")
                                                  //> data  : List[String] = List(a, a, a, b, c, c, a)
  pack(data)                                      //> res6: List[List[String]] = List(List(a, a, a), List(b), List(c, c), List(a))
                                                  //| 
  def encode[T](xs: List[T]): List[(T, Int)] = {
    pack(xs) map { x => (x.head, x.size) }
  }                                               //> encode: [T](xs: List[T])List[(T, Int)]
  encode(data)                                    //> res7: List[(String, Int)] = List((a,3), (b,1), (c,2), (a,1))

  def concat[T](xs: List[T], ys: List[T]): List[T] =
    (xs foldRight ys)(_ :: _)                     //> concat: [T](xs: List[T], ys: List[T])List[T]
  concat(List(2, 3, 5), List(4, 6, 8))            //> res8: List[Int] = List(2, 3, 5, 4, 6, 8)

	//comlete the implementaiton such that foldRight applies
 	def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    (xs foldRight List[U]())(???)                 //> mapFun: [T, U](xs: List[T], f: T => U)List[U]

	//comlete the implementaiton such that foldRight applies
  def lengthFun[T](xs: List[T]): Int =
    (xs foldRight 0)(???)                         //> lengthFun: [T](xs: List[T])Int
}