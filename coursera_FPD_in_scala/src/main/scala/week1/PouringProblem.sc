package week1

object PouringProblem {
  val problem = new Pouring(Vector(4,9))          //> problem  : week1.Pouring = week1.Pouring@6df97b55
  problem.moves                                   //> res0: scala.collection.immutable.IndexedSeq[Product with Serializable with we
                                                  //| ek1.PouringProblem.problem.Move] = Vector(Empty(0), Empty(1), Fill(0), Fill(1
                                                  //| ), Pour(0,1), Pour(1,0))
	problem.pathSets.take(3).toList           //> res1: List[Set[week1.PouringProblem.problem.Path]] = List(Set(--> Vector(0, 
                                                  //| 0)), Set(Fill(0)--> Vector(4, 0), Fill(1)--> Vector(0, 9)), Set(Fill(0) Fill
                                                  //| (1)--> Vector(4, 9), Fill(0) Pour(0,1)--> Vector(0, 4), Fill(1) Fill(0)--> V
                                                  //| ector(4, 9), Fill(1) Pour(1,0)--> Vector(4, 5)))
  val iPath = Set(problem.initialPath)            //> iPath  : scala.collection.immutable.Set[week1.PouringProblem.problem.Path] =
                                                  //|  Set(--> Vector(0, 0))
                                                  
  //if(iPath.isEmpty) "empty" else "not empty"
  
  problem.solution(6)                             //> res2: Stream[week1.PouringProblem.problem.Path] = Stream(Fill(1) Pour(1,0) E
                                                  //| mpty(0) Pour(1,0) Empty(0) Pour(1,0) Fill(1) Pour(1,0)--> Vector(4, 6), ?)
}