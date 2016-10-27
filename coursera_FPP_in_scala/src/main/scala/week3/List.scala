package week3

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def nth(n: Int, list: List[T]): T
}

class Cons[T](val head: T, val tail: List[T]) extends List[T]{
  
  /*
   * In Scala val definitions(field definitions and classes) are 
   * special cases of methods and they can override methods and implement
   * abstract methods in traits
   * 
   * Difference between val and def concerns only initialisation
   * A val is evaluated when object is first initialised
   * A def is evaluated everytime it is referenced
   */
  
  def isEmpty = false //cons cells are never empty
  
  def nth(n: Int, list: List[T]): T = ???
}

class Nil[T] extends List[T]{
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
  def nth(n: Int, list: List[T]): Nothing = throw new NoSuchElementException("Nil.nth")
}