package week1

object RandomValueGenerator {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(88); 
  println("Welcome to the Scala worksheet");$skip(116); 
 	
 	//Random Int generator
 	val integers = new Generator[Int] {
 		def generate = scala.util.Random.nextInt()
 	};System.out.println("""integers  : week1.Generator[Int] = """ + $show(integers ));$skip(113); 
	//Random Boolean generator
 	val booleans = new Generator[Boolean]{
 		def generate = integers.generate > 0
 	};System.out.println("""booleans  : week1.Generator[Boolean] = """ + $show(booleans ));$skip(124); 
 	
 	//Random pair generator
 	def pairs[T, U](t: Generator[T], u: Generator[U]) = for{
 		x <- t
 		y <- u
 	} yield (x,y);System.out.println("""pairs: [T, U](t: week1.Generator[T], u: week1.Generator[U])week1.Generator[(T, U)]""");$skip(143); 
	//building blocks for other generators like list and tree etc
 	def single[T](x: T): Generator[T] = new Generator[T]{
 		def generate = x
 	};System.out.println("""single: [T](x: T)week1.Generator[T]""");$skip(100); 
 	
 	def choose(lo: Int, hi: Int): Generator[Int] =
 		for (x <- integers) yield lo + x % (hi - lo);System.out.println("""choose: (lo: Int, hi: Int)week1.Generator[Int]""");$skip(88); 
	def oneOf[T](xs: T*): Generator[T] =
		for (idx <- choose(0, xs.length)) yield xs(idx);System.out.println("""oneOf: [T](xs: T*)week1.Generator[T]""");$skip(157); 
	//Random List generator
	
	def lists: Generator[List[Int]] = for {
		isEmpty <- booleans
		list <- if (isEmpty) emptyLists else nonEmptyLists
	} yield list;System.out.println("""lists: => week1.Generator[List[Int]]""");$skip(32); 
	
	def emptyLists = single(Nil);System.out.println("""emptyLists: => week1.Generator[scala.collection.immutable.Nil.type]""");$skip(114); 
	                            
	def nonEmptyLists = for {
		head <- integers
		tail <- lists
	}	yield head :: tail;System.out.println("""nonEmptyLists: => week1.Generator[List[Int]]""");$skip(97); 
	
	//Random Tree generator
	
	def leafs: Generator[Leaf] = for{
		x <- integers
	} yield Leaf(x);System.out.println("""leafs: => week1.Generator[week1.Leaf]""");$skip(85); 
	
	def inners: Generator[Inner] = for{
		l <- trees
		r <- trees
	} yield Inner(l,r);System.out.println("""inners: => week1.Generator[week1.Inner]""");$skip(164); 
	
	def trees: Generator[Tree] = for {
		isLeaf <- booleans //flip a coin, whether we want a leaf or inner node
		tree <- if(isLeaf) leafs else inners
	} yield tree;System.out.println("""trees: => week1.Generator[week1.Tree]""");$skip(18); val res$0 = 
	
	trees.generate;System.out.println("""res0: week1.Tree = """ + $show(res$0));$skip(289); 
	//Random test function using generators
	def test[T](g: Generator[T], numTimes: Int = 100)
		(test: T => Boolean): Unit = {
		for(i <- 0 until numTimes){
			val value = g.generate
			assert((test(value)), "Test failed for " + value)
		}
		println("Test passed " + numTimes + " tests")
	};System.out.println("""test: [T](g: week1.Generator[T], numTimes: Int)(test: T => Boolean)Unit""");$skip(82); 
	
	test(pairs(lists, lists)){
		case (xs, ys) => (xs ++ ys).length > xs.length
	}}
}

trait Tree //a tree is either a Leaf or an inner node.
case class Inner(left: Tree, right: Tree) extends Tree
case class Leaf(x: Int) extends Tree

trait Generator[+T]{
	self => // an alias for "this"
	
	def generate: T
	
	def map[S](f: T => S): Generator[S] = new Generator[S] {
		//def generate = f(Generator.this.generate) //or
		def generate = f(self.generate) //need to refer to alias "self" to call
																		//generate outside map else it would call
																		//anonymous method generate which will result
																		//in infinite loop
	}
	
	def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
		def generate = f(self.generate).generate
	}
	
}
