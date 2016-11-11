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
	
	val list = List('a','b','e','a','f','e','a')
	val res = list.groupBy(identity).mapValues(_.size) toList
	var sortedRes = res sortBy(_._2)
	var leafs = res.sortBy(_._2).map{ case (char,weight) => Leaf(char,weight) }
	
	var list2 = List('e','f')
	(list diff list2) :+ 'x'
	
	val aList = List(Leaf('b',1), Leaf('e',2), Leaf('f',4), Leaf('a',7),Fork(Leaf('x',3),Leaf('y',7),List('x','y'),10))
	combine(aList)
	
	createCodeTree(string2Chars("aybayxafyaeyaxyafyafefyx"))
  //decode(createCodeTree(string2Chars("aybayxafyaeyaxyafyafefyx")), List(1,0,0,0,0,1,0,0))
 	//decodedSecret
 	encode(createCodeTree(string2Chars("aybayxafyaeyaxyafyafefyx")))(List('y', 'f', 'b'))
 	
 	codeBits(List(('c',List(0,0)),('a',List(0,1)),('x',List(1,0)),('y',List(1,1))))('a')
  val codeTree = Fork(Fork(Leaf('f',4),Fork(Fork(Leaf('b',1),Leaf('e',2),List('b', 'e'),3),Leaf('x',3),List('b', 'e', 'x'),6),List('f', 'b', 'e', 'x'),10),Fork(Leaf('y',7),Leaf('a',7),List('y', 'a'),14),List('f', 'b', 'e', 'x', 'y', 'a'),24)
  convert(codeTree)
                                                  
	quickEncode(createCodeTree(string2Chars("aybayxafyaeyaxyafyafefyx")))(List('y', 'f', 'b'))
  
}