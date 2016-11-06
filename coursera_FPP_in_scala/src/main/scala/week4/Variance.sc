package week4

object Variance {
  println("Welcome to the Scala worksheet")
  
  /*
  Some types should be the covariant, but others should not
  
  A type that accepts mutations of its elements should NOT be the covariant
  
  Mutable types can be covaiant, if some conditions on methods are met
  
  Say C[T] is a parameterized type and A, B are types such that A <: B
  In general there are three possible relationships between C[A] and C[B]
  
  if C[A] <: C[B] then C is covariant
  if C[A] >: C[B] then C is contravariant
  if neither C[A] nor C[B] is a subtype of other then C is nonvariant
  
  scala lets you declare the variance of a type by annotating the type parameter
  
  class C[+A] {...} declares that C is a covariant
  class C[-A] {...} declares that C is contravariant
  class C[A] {....} declares that C is a nonvariant i.e. C[A] and C[B] are not related
  
  ------------------------------------------------------------------------------------
  There is following rule for subtying between funtion types
  
  if A2 <: A1 and B1 <: B2
  
  then A1 => B1 <: A2 => B2
  (so return types are in the same order but argument types are in the reverse order)
  
  So functions are 'contravariant' in argument type(s) but 'covariant' in result type
  
  This leads to following revised definition of the Funution1 trait
  
  trait Function1(-T, +U){
    def apply(x: T): U
  }
  -------------------------------------------------------------------------------------
  Does this mean that variance annotation can be used anywhere to make types covariant
  or contravariant? OBVIOUSLY NOT
  
  The scala compiler checks that there are no problematic combinations when compiling
  a class with variance notation
  Roughly;
  1) Covariant type parameters can only appear in method return types
  2) Contravariant type parameters can only appear in method parameters
  3) Invariant type parameters can appear anywhere
  
  The precise rules are bit more involved, fortunately Scala compilers performs them
  for us
  -------------------------------------------------------------------------------------
  
  */
  
}