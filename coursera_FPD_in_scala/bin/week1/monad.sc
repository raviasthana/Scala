package week1

object monad {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
}

/*
* A monad M is a parametric type M[T] with two operations, flatMap and
* unit, that have to satisfy some laws
*
* In the literature, flatMap is more commonly called "bind".
*
*	List is a monad with unit(x) = List(x)
* Set is a monad with unit(x) = Set(x)
* Option is a monad with unit(x) = Some(x)
* Generator is a monad with unit(x) = single(x)
* flatMap is an operation with each of these types, whereas unit
* in Scala is different for each monad.
*
* Map can be defined for every monad as a combination of flatMap and unit:
* m map f == m flatMap (x => unit(f(x)))
*					== m flatMap (f andThen unit)
*
* To qualify as a monad, a type has to satisfy three laws:
* Associativity:
* 	(m flatMap f) flatMap g == m flatMap ((x => f(x) flatMap g))
* Left unit
*		unit(x) flatMap f == f(x)
*	Right unit
* 	m flatMap unit = m
*
* We have seen that monad-typed expressions are typically written
* as for expressions.
* Associativity allows the "inlining" of "nested" for expressions:
* e.g. expression
*				for(y <- for(x <-m; y <- f(x)) yield y
*					 z <- g(y)) yield z
*
* can be written in much more intuitive form as:
*
* for (x <- m
*			 y <- f(x)
*			 z <- g(y) ) yield z
*
*	Right unit says:
* for(x <- m) yield x == m
*
*
*/

trait M[T] {
	def flatMap[U](f: T => M[U]): M[U]
	
	def unit[T](x: T): M[T]
}