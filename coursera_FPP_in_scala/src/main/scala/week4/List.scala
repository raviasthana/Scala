package week4

import week3.assignment.objsets.NonEmpty
import week3.assignment.objsets.Empty

trait List[+T] {

  def isEmpty: scala.Boolean
  def head: T
  def tail: List[T]
  //def nth[T](n: Int, list: List[T]): T
  def prepend[U >: T](elem: U): List[U] = new Cons(elem, this) /* T is declared co-variant
  * and hence can't appear in method type parameter as it will violate the variance check.
  * However, co-variant type parameter can appear in lower bounds of method type parameters.
  * and contra-variant type parameters may appear in upper bounds of method.
  */
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
  /*
  def nth[T](n: Int, list: List[T]): T = {
    def loop(index: Int, tList: List[T]): T = {
      if(tList.isEmpty) throw new IndexOutOfBoundsException("Index value [" + index + "] is out of bound")
      else if(index == n) tList.head 
      else loop(index + 1, tList.tail)
    }
    if(n < 0) throw new IndexOutOfBoundsException("Index value [" + n + "] is out of bound")
    else loop(0,list)
  }*/
}

object Nil extends List[Nothing]{ //object cannot have a parameter type because there is only one instance of it
  def isEmpty: scala.Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
  //def nth[T](n: Int, list: List[T]): Nothing = throw new NoSuchElementException("Nil.nth")
}

object List {
  // List(1,2) = List.apply(1, 2)
  def apply[T](x1: T, x2: T) = new Cons(x1, new Cons(x2, Nil))
  def apply[T]() = Nil
}

object test {
  val x: List[String] = Nil //this results in compiler error as Nil which extends List[Nothing] is not
                            //a covariant with List[String]. However, as Nothing is a subtype of String
                            //we can define type T as a covriant i.e. +T and then List[String] will
                            //become covariant with List[Nothing] and then above assignment will become valid
  
  def f(xs: List[NonEmpty], x: Empty) = xs prepend x
}