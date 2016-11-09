package week4.assignment.patmat

object huffmanWS {
	import week4.assignment.patmat.Huffman._
  println("Welcome to the Scala worksheet")

  val sampleTree = makeCodeTree(
    makeCodeTree(Leaf('x', 1), Leaf('e', 1)),
    Leaf('t', 2))
    
  weight(Leaf('x',5))
  weight(Fork(Leaf('x',5),Leaf('y',2),List('x','y'),7))
	chars(Fork(Leaf('x',5),Leaf('y',2),List('x','y'),7))
	
	val list = scala.List('a','b','e','a','f','e','a')
	val res = list.groupBy(identity).mapValues(_.size) toList
	var sortedRes = res sortBy(_._2)
	var leafs = res.sortBy(_._2).map{ case (char,weight) => Leaf(char,weight) }
	
	var list2 = scala.List('e','f')
	(list diff list2) :+ 'x'
	
	val aList = scala.List(Leaf('b',1), Leaf('e',2), Leaf('f',4), Leaf('a',7),Fork(Leaf('x',3),Leaf('y',7),List('x','y'),10))
	combine(aList)
	
	
}