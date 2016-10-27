package week3

object intsets {
  
  //val t1 = new NonEmpty(3, new Empty, new Empty)
  val t1 = new NonEmpty(3, Empty, Empty)          //> t1  : week3.NonEmpty = {.3.}
  val t2 = t1 incl 4                              //> t2  : week3.IntSet = {.3{.4.}}
  val t3 = new NonEmpty(7, Empty, Empty)          //> t3  : week3.NonEmpty = {.7.}
  val t4 = t3 incl 6 incl 8                       //> t4  : week3.IntSet = {{.6.}7{.8.}}
  
  val t5 = t2 union t4                            //> {.3{.4.}}
                                                  //| {{.6.}7{.8.}}
                                                  //| {.4.}
                                                  //| {{.6.}7{.8.}}
                                                  //| t5  : week3.IntSet = {{{{.3.}4.}6.}7{.8.}}
}

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

  //object creates a singleton
  //there is no need to create multiple instances of Empty
  //as there is always only one Empty IntSet
  //Empty is created when you reference is for the first time
  /*class*/ object Empty extends IntSet {
  def contains(x: Int): Boolean = false
  //def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  
  def union(other: IntSet) = other
  
  override def toString = "."
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  
  def contains(x: Int): Boolean = {
    if(x < elem) left contains x
    else if (x > elem) right contains x
    else true
  }
  
  def incl(x: Int): IntSet = {
    if(x < elem) new NonEmpty(elem, left incl x, right)
    else if(x > elem) new NonEmpty(elem, left, right incl x)
    else this
  }
  
  def union(other: IntSet): IntSet = {
    println(this.toString)
    println(other.toString)
    ((left union right) union other) incl elem
  }

  override def toString = "{" + left + elem + right + "}"
}