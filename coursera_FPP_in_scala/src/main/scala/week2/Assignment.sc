package week2

object Assignment {
  //define the abstraction of a Set(a function that has the ability to tell
  //that element is in set or not)
  type Set = Int => Boolean
  
  def contains(s: Set, elem: Int): Boolean = s(elem)
                                                  //> contains: (s: week2.Assignment.Set, elem: Int)Boolean
  
  def singletonSet(elem: Int): Set = x => x == elem
                                                  //> singletonSet: (elem: Int)week2.Assignment.Set
  contains(singletonSet(1),1)                     //> res0: Boolean = true
  contains(singletonSet(2),1)                     //> res1: Boolean = false
  
  val mySet: Int => Boolean = x => Array(0,1,2,3,5,8) contains x
                                                  //> mySet  : Int => Boolean = <function1>
  mySet(3)                                        //> res2: Boolean = true
  mySet(9)                                        //> res3: Boolean = false
  
  def union(s: Set, t: Set): Set = x => s(x) || t(x)
                                                  //> union: (s: week2.Assignment.Set, t: week2.Assignment.Set)week2.Assignment.Se
                                                  //| t
  contains(union(Set(1,2),Set(1,3)),2)            //> res4: Boolean = true
  contains(union(Set(1,2),Set(1,3)),3)            //> res5: Boolean = true
  contains(union(Set(1,2),Set(1,3)),1)            //> res6: Boolean = true
  contains(union(Set(1,2),Set(1,3)),4)            //> res7: Boolean = false
  
  def intersect(s: Set, t: Set): Set = x => s(x) && t(x)
                                                  //> intersect: (s: week2.Assignment.Set, t: week2.Assignment.Set)week2.Assignmen
                                                  //| t.Set
  contains(intersect(Set(1,2),Set(1,3)),2)        //> res8: Boolean = false
  contains(intersect(Set(1,2),Set(1,3)),3)        //> res9: Boolean = false
  contains(intersect(Set(1,2),Set(1,3)),1)        //> res10: Boolean = true
  
  def diff(s: Set, t: Set): Set = x => s(x) && !t(x)
                                                  //> diff: (s: week2.Assignment.Set, t: week2.Assignment.Set)week2.Assignment.Set
                                                  //| 
  contains(diff(Set(1,2,5),Set(1,3)),2)           //> res11: Boolean = true
  contains(diff(Set(1,2,5),Set(1,3)),5)           //> res12: Boolean = true
  contains(diff(Set(1,2,5),Set(1,3)),1) == false  //> res13: Boolean = true
  
  def filter(s: Set, p: Int => Boolean): Set = x => s(x) && p(x)
                                                  //> filter: (s: week2.Assignment.Set, p: Int => Boolean)week2.Assignment.Set
  contains(filter(Set(1,2,5),(x: Int) => x > 2),2) == false
                                                  //> res14: Boolean = true
  contains(filter(Set(1,2,5),(x: Int) => x > 0),1) == true
                                                  //> res15: Boolean = true
  contains(filter(Set(1,2,5),(x: Int) => x > 0),6) == false
                                                  //> res16: Boolean = true
  
  //refer to following articlet for some understanding
  //http://stackoverflow.com/questions/13052735/is-my-understanding-of-below-scala-code-correct
}