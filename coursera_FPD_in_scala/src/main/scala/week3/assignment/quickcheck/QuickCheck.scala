package week3.assignment.quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._
import Math._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = for {
    a <- arbitrary[A]
    //h <- frequency((1,empty),(9, genHeap))
    h <- oneOf(const(empty),genHeap)
  } yield insert(a,h)
  
  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("findMin should return the minimum of two added") = forAll { 
    (a: A, b: A) =>
      val h = insert(a, insert(b, empty)) 
      val smallest = if(a < b) a else b
      findMin(h) == smallest
  }
  
  property("deleting only element in heap should result in empty heap") = forAll {
    (a: A) =>
      val h = insert(a, empty)
      isEmpty(deleteMin(h))
  }
  
  property("finding and deleting should get a sorted sequence of elements") = forAll {
    (h: H) =>
      def isSorted(h: H): Boolean =
        if(isEmpty(h)) true
        else {
          val m = findMin(h)
          val h2 = deleteMin(h)
          isEmpty(h2) || (m <= findMin(h2) && isSorted(h2))
        }
          
    isSorted(h)
  }
  
  property("minimum of melding of two heaps should return min of one or other heap") = forAll {
    (h1: H, h2: H) =>
      findMin(meld(h1,h2)) == min(findMin(h1), findMin(h2))
  }
  
  property("heap equality") = forAll { (h1: H, h2: H) =>  
    def heapEqual(h1: H, h2: H): Boolean =
      if (isEmpty(h1) && isEmpty(h2)) true
      else {
        val m1 = findMin(h1)
        val m2 = findMin(h2)
        m1 == m2 && heapEqual(deleteMin(h1), deleteMin(h2))
      }
    
    heapEqual(meld(h1, h2),
              meld(deleteMin(h1), insert(findMin(h1), h2)))
  }
  
}


