package week4.assignment.patmat

object huffmanWS {
	import week4.assignment.patmat.Huffman._
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val sampleTree = makeCodeTree(
    makeCodeTree(Leaf('x', 1), Leaf('e', 1)),
    Leaf('t', 2))                                 //> sampleTree  : week4.assignment.patmat.Huffman.Fork = Fork(Fork(Leaf(x,1),Lea
                                                  //| f(e,1),List(x, e),2),Leaf(t,2),List(x, e, t),4)
    
  weight(Leaf('x',5))                             //> res0: Int = 5
  weight(Fork(Leaf('x',5),Leaf('y',2),List('x','y'),7))
                                                  //> res1: Int = 7
	chars(Fork(Leaf('x',5),Leaf('y',2),List('x','y'),7))
                                                  //> res2: List[Char] = List(x, y)
	
	val list = List('a','b','e','a','f','e','a')
                                                  //> list  : List[Char] = List(a, b, e, a, f, e, a)
	val res = list.groupBy(identity).mapValues(_.size) toList
                                                  //> res  : List[(Char, Int)] = List((e,2), (b,1), (a,3), (f,1))
	var sortedRes = res sortBy(_._2)          //> sortedRes  : List[(Char, Int)] = List((b,1), (f,1), (e,2), (a,3))
	var leafs = res.sortBy(_._2).map{ case (char,weight) => Leaf(char,weight) }
                                                  //> leafs  : List[week4.assignment.patmat.Huffman.Leaf] = List(Leaf(b,1), Leaf(f
                                                  //| ,1), Leaf(e,2), Leaf(a,3))
	
	var list2 = List('e','f')                 //> list2  : List[Char] = List(e, f)
	(list diff list2) :+ 'x'                  //> res3: List[Char] = List(a, b, a, e, a, x)
	
	val aList = List(Leaf('b',1), Leaf('e',2), Leaf('f',4), Leaf('a',7),Fork(Leaf('x',3),Leaf('y',7),List('x','y'),10))
                                                  //> aList  : List[Product with Serializable with week4.assignment.patmat.Huffman
                                                  //| .CodeTree] = List(Leaf(b,1), Leaf(e,2), Leaf(f,4), Leaf(a,7), Fork(Leaf(x,3)
                                                  //| ,Leaf(y,7),List(x, y),10))
	combine(aList)                            //> res4: List[week4.assignment.patmat.Huffman.CodeTree] = List(Fork(Fork(Leaf(x
                                                  //| ,3),Leaf(y,7),List(x, y),10),Fork(Fork(Fork(Leaf(b,1),Leaf(e,2),List(b, e),3
                                                  //| ),Leaf(f,4),List(b, e, f),7),Leaf(a,7),List(b, e, f, a),14),List(x, y, b, e,
                                                  //|  f, a),24))
	
	createCodeTree(string2Chars("aybayxafyaeyaxyafyafefyx"))
                                                  //> res5: week4.assignment.patmat.Huffman.CodeTree = Fork(Fork(Leaf(f,4),Fork(Fo
                                                  //| rk(Leaf(b,1),Leaf(e,2),List(b, e),3),Leaf(x,3),List(b, e, x),6),List(f, b, e
                                                  //| , x),10),Fork(Leaf(y,7),Leaf(a,7),List(y, a),14),List(f, b, e, x, y, a),24)
                                                  //| 
  //decode(createCodeTree(string2Chars("aybayxafyaeyaxyafyafefyx")), List(1,0,0,0,0,1,0,0))
 	//decodedSecret
 	encode(createCodeTree(string2Chars("aybayxafyaeyaxyafyafefyx")))(List('y', 'f', 'b'))
                                                  //> res6: List[week4.assignment.patmat.Huffman.Bit] = List(1, 0, 0, 0, 0, 1, 0,
                                                  //|  0)
 	
 	codeBits(List(('c',List(0,0)),('a',List(0,1)),('x',List(1,0)),('y',List(1,1))))('a')
                                                  //> res7: List[week4.assignment.patmat.Huffman.Bit] = List(0, 1)
  val codeTree = Fork(Fork(Leaf('f',4),Fork(Fork(Leaf('b',1),Leaf('e',2),List('b', 'e'),3),Leaf('x',3),List('b', 'e', 'x'),6),List('f', 'b', 'e', 'x'),10),Fork(Leaf('y',7),Leaf('a',7),List('y', 'a'),14),List('f', 'b', 'e', 'x', 'y', 'a'),24)
                                                  //> codeTree  : week4.assignment.patmat.Huffman.Fork = Fork(Fork(Leaf(f,4),Fork
                                                  //| (Fork(Leaf(b,1),Leaf(e,2),List(b, e),3),Leaf(x,3),List(b, e, x),6),List(f, 
                                                  //| b, e, x),10),Fork(Leaf(y,7),Leaf(a,7),List(y, a),14),List(f, b, e, x, y, a)
                                                  //| ,24)
  convert(codeTree)                               //> res8: week4.assignment.patmat.Huffman.CodeTable = List((f,List(0, 0)), (b,L
                                                  //| ist(0, 1, 0, 0)), (e,List(0, 1, 0, 1)), (x,List(0, 1, 1)), (y,List(1, 0)), 
                                                  //| (a,List(1, 1)))
                                                  
	quickEncode(createCodeTree(string2Chars("aybayxafyaeyaxyafyafefyx")))(List('y', 'f', 'b'))
                                                  //> res9: List[week4.assignment.patmat.Huffman.Bit] = List(1, 0, 0, 0, 0, 1, 0,
                                                  //|  0)
	
  
}