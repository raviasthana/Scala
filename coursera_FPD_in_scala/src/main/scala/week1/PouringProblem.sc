package week1

object PouringProblem {
  val problem = new Pouring(Vector(4,9,19))       //> problem  : week1.Pouring = week1.Pouring@34b7bfc0
  problem.moves                                   //> res0: scala.collection.immutable.IndexedSeq[Product with Serializable with we
                                                  //| ek1.PouringProblem.problem.Move] = Vector(Empty(0), Empty(1), Empty(2), Fill(
                                                  //| 0), Fill(1), Fill(2), Pour(0,1), Pour(0,2), Pour(1,0), Pour(1,2), Pour(2,0), 
                                                  //| Pour(2,1))
	problem.pathSets.take(3).toList           //> res1: List[Set[week1.PouringProblem.problem.Path]] = List(Set(--> Vector(0, 
                                                  //| 0, 0)), Set(Fill(0)--> Vector(4, 0, 0), Fill(1)--> Vector(0, 9, 0), Fill(2)-
                                                  //| -> Vector(0, 0, 19)), Set(Fill(0) Fill(2)--> Vector(4, 0, 19), Fill(0) Fill(
                                                  //| 1)--> Vector(4, 9, 0), Fill(1) Fill(2)--> Vector(0, 9, 19), Fill(2) Pour(2,1
                                                  //| )--> Vector(0, 9, 10), Fill(2) Pour(2,0)--> Vector(4, 0, 15), Fill(1) Pour(1
                                                  //| ,2)--> Vector(0, 0, 9), Fill(0) Pour(0,2)--> Vector(0, 0, 4), Fill(1) Pour(1
                                                  //| ,0)--> Vector(4, 5, 0), Fill(1) Fill(0)--> Vector(4, 9, 0), Fill(0) Pour(0,1
                                                  //| )--> Vector(0, 4, 0), Fill(2) Fill(0)--> Vector(4, 0, 19), Fill(2) Fill(1)--
                                                  //| > Vector(0, 9, 19)))
  val iPath = Set(problem.initialPath)            //> iPath  : scala.collection.immutable.Set[week1.PouringProblem.problem.Path] =
                                                  //|  Set(--> Vector(0, 0, 0))
                                                  
  //if(iPath.isEmpty) "empty" else "not empty"
  
  problem.solution(17)                            //> res2: Stream[week1.PouringProblem.problem.Path] = Stream(Fill(0) Pour(0,2) F
                                                  //| ill(1) Fill(0) Pour(1,2) Pour(0,2)--> Vector(0, 0, 17), ?)
}