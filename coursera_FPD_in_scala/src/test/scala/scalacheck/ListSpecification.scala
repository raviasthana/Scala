package scalacheck

import org.scalacheck._
import Prop.forAll

object ListSpecification extends Properties("List") {
  
  property("double reverse") = forAll { (lst: List[Int]) =>
    lst.reverse.reverse == lst
  }
  
  //this property DOES NOT always hold
  //property always fails when the list don't have the same length
  /*
  property("zip reverse") = forAll { (a: List[Int], b: List[Int]) =>
    (a.reverse zip b.reverse) == (a zip b).reverse    
  }*/
  
  //zip reverse fixed
  //ensure both lists have the same length
  property("zip reverse fixed") = forAll { (a: List[Int], b: List[Int]) =>
    val a1 = a.take(b.length)
    val b1 = b.take(a.length)
    (a1.reverse zip b1.reverse) == (a1 zip b1).reverse    
  }
  
  //this property DOES NOT always hold
  //if they key passed is same as an existing one then map will just update
  //the value corresponding to the key so map length will remain the same as original
  property("+ and size") = forAll { (m: Map[Int, Int], key: Int, value: Int) =>
    (m + (key -> value)).size == (m.size + 1)
  }
  
}