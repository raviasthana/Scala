package week6.assignment.forcomp

import week6.assignment.forcomp.Anagrams._

object AnagramsPractice {

  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  wordOccurrences("Every")                        //> res0: week6.assignment.forcomp.Anagrams.Occurrences = List((e,2), (v,1), (y,
                                                  //| 1), (r,1))
  
  //sentenceOccurrences(List("I", "Love", "You"))
  sentenceOccurrences(List("Yes", "man"))
  dictionaryByOccurrences
  
  wordAnagrams("sane")
  
  
  combinations(List(('a', 2), ('b', 2))) mkString "\n"
                                 
  val l = List(('a',2),('b',2))
  val l2 = List(('b',3))
  
  l ++ l2
  l :: l2
  l ::: l2
  
  /* :: is applied on right hand operand i.e. b in this case
  * so right hand operand i.e. b should be of type list as
  * :: is application on list type only
  *
  * in the following example b is the inital value passed to the function
  * in this case it is List[Int]() and a is the element from the list, int values in this case
  * a :: b means that int value a is being added at the start of list b
  *
  * foldLeft starts at the head and works its way to the end of the list
  * foldRight starts at the end of the list and works its way to the head
  */
  List(2,3).foldLeft(List[Int]())((b,a) => a :: b)
  // notice in foldRight a (i.e parameter at the right hand side of (b,a)) is the intial parameter passed
  // and b is the value from the list
  List(2,3).foldRight(List[Int]())((b,a) => b :: a)
  //val l = List(('a',2),('b',2))
  //for((char,num) <- l; n <- 1 to num) yield (char, n)
  //val l1 = List(List()) ::: List(List(('a',1)))
  //val l2 = l1 ::: List(List(('a',2)))
  //val l3 = l2 ::: List(List(('b',1),('b',2)))
	val x = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
	val y = List(('r', 1))
	subtract(x, y)
	//sentenceAnagrams(List("Yes", "man"))
	/*
	for (i <- 1 to 10; j <- 1 until i)
    yield (i, j)
    
  (1 to 10).flatMap(
    x => (1 until x).map(
      y => (x, y)
    )
  )*/
}