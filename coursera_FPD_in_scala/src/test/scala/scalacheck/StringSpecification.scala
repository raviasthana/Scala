package scalacheck

import org.scalacheck._
import Prop.forAll

object StringSpecification extends Properties("String") {
  
  property("startsWith") = forAll { (x: String, y: String) =>
    (x + y).startsWith(x)
  }
  
  property("concatenation length") = forAll { (x: String, y: String) =>
    x.length <= (x + y).length
  }
}