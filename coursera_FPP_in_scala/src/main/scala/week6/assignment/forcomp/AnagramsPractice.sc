package week6.assignment.forcomp

import week6.assignment.forcomp.Anagrams._

object AnagramsPractice {
	
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  wordOccurrences("Every")                        //> res0: week6.assignment.forcomp.Anagrams.Occurrences = List((e,2), (v,1), (y,
                                                  //| 1), (r,1))
  sentenceOccurrences(List("I","Love","You"))     //> res1: week6.assignment.forcomp.Anagrams.Occurrences = List((e,1), (y,1), (u,
                                                  //| 1), (i,1), (v,1), (l,1), (o,2))
	//dictionaryByOccurrences
  //wordAnagrams("eat")
	combinations(List(('a',2),('b',2))) mkString "\n"
                                                  //> res2: String = List()
                                                  //| List((a,2))
                                                  //| List((a,1))
                                                  //| List((b,2))
                                                  //| List((b,1))
	
	
}