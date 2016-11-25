package scalacheck

import org.scalacheck._
import Prop.forAll

object CustomDatatypeSpecification extends Properties("Tree") {
  
  val ints = Gen.choose(-100, 100)
  
  def leafs: Gen[Leaf] = for {
    x <- ints
  } yield Leaf(x)
  
  def nodes: Gen[Node] = for {
    left <- trees
    right <- trees
  } yield Node(left, right)
  
  def trees = Gen.oneOf(leafs, nodes)
}

object VectorSpecification extends Properties("Vector") {
  
  //this property will FAIL if s is NEGATIVE
  property("* and length") = forAll(vectors, ints) { (v: Vector, s: Int) =>
    if(s >= 1.0) (v * s).length >= v.length
    else (v * s).length < v.length
  }
  
  //val ints = Arbitrary.arbitrary[Int]
  //POSITIVE only
  val ints = Gen.choose(1, 100)
  
  val vectors: Gen[Vector] =
    for {
      x <- Gen.choose(-100, 100)
      y <- Gen.choose(-100, 100)
    } yield Vector(x, y)
}

// custom case class describing two-dimensional mathematical vectors
case class Vector(x: Int, y: Int){
  //scalar multiplication
  def *(s: Int) = Vector(x * s, y * s)
  def length: Double = math.sqrt(x * x + y * y)
}

// custom datatype describing binary tree of integers
trait Tree{
  def depth: Int
}
case class Node(left: Tree, right: Tree) extends Tree{
  def depth = 1
}
case class Leaf(x: Int) extends Tree{
  def depth = 1
}