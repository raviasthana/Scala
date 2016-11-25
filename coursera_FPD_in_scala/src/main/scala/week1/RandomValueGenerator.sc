package week1

object RandomValueGenerator {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
 	
 	//Random Int generator
 	val integers = new Generator[Int] {
 		def generate = scala.util.Random.nextInt()
 	}                                         //> integers  : week1.Generator[Int] = week1.RandomValueGenerator$$anonfun$main$
                                                  //| 1$$anon$1@34ce8af7
	//Random Boolean generator
 	val booleans = new Generator[Boolean]{
 		def generate = integers.generate > 0
 	}                                         //> booleans  : week1.Generator[Boolean] = week1.RandomValueGenerator$$anonfun$m
                                                  //| ain$1$$anon$2@b684286
 	
 	//Random pair generator
 	def pairs[T, U](t: Generator[T], u: Generator[U]) = for{
 		x <- t
 		y <- u
 	} yield (x,y)                             //> pairs: [T, U](t: week1.Generator[T], u: week1.Generator[U])week1.Generator[(
	                                                 //| T, U)]
	//building blocks for other generators like list and tree etc
 	def single[T](x: T): Generator[T] = new Generator[T]{
 		def generate = x
 	}                                         //> single: [T](x: T)week1.Generator[T]
 	
 	def choose(lo: Int, hi: Int): Generator[Int] =
 		for (x <- integers) yield lo + x % (hi - lo)
                                                  //> choose: (lo: Int, hi: Int)week1.Generator[Int]
	def oneOf[T](xs: T*): Generator[T] =
		for (idx <- choose(0, xs.length)) yield xs(idx)
                                                  //> oneOf: [T](xs: T*)week1.Generator[T]
	//Random List generator
	
	def lists: Generator[List[Int]] = for {
		isEmpty <- booleans
		list <- if (isEmpty) emptyLists else nonEmptyLists
	} yield list                              //> lists: => week1.Generator[List[Int]]
	
	def emptyLists = single(Nil)              //> emptyLists: => week1.Generator[scala.collection.immutable.Nil.type]
	                            
	def nonEmptyLists = for {
		head <- integers
		tail <- lists
	}	yield head :: tail                //> nonEmptyLists: => week1.Generator[List[Int]]
	
	//Random Tree generator
	
	def leafs: Generator[Leaf] = for{
		x <- integers
	} yield Leaf(x)                           //> leafs: => week1.Generator[week1.Leaf]
	
	def inners: Generator[Inner] = for{
		l <- trees
		r <- trees
	} yield Inner(l,r)                        //> inners: => week1.Generator[week1.Inner]
	
	def trees: Generator[Tree] = for {
		isLeaf <- booleans //flip a coin, whether we want a leaf or inner node
		tree <- if(isLeaf) leafs else inners
	} yield tree                              //> trees: => week1.Generator[week1.Tree]
	
	trees.generate                            //> res0: week1.Tree = Inner(Inner(Leaf(-849076782),Leaf(1697470776)),Leaf(-208
                                                  //| 8773535))
	//Random test function using generators
	def test[T](g: Generator[T], numTimes: Int = 100)
		(test: T => Boolean): Unit = {
		for(i <- 0 until numTimes){
			val value = g.generate
			assert((test(value)), "Test failed for " + value)
		}
		println("Test passed " + numTimes + " tests")
	}                                         //> test: [T](g: week1.Generator[T], numTimes: Int)(test: T => Boolean)Unit
	
	test(pairs(lists, lists)){
		case (xs, ys) => (xs ++ ys).length > xs.length
	}                                         //> java.lang.AssertionError: assertion failed: Test failed for (List(),List())
                                                  //| 
                                                  //| 	at scala.Predef$.assert(Predef.scala:170)
                                                  //| 	at week1.RandomValueGenerator$$anonfun$main$1$$anonfun$test$1$1.apply$mc
                                                  //| VI$sp(week1.RandomValueGenerator.scala:64)
                                                  //| 	at scala.collection.immutable.Range.foreach$mVc$sp(Range.scala:160)
                                                  //| 	at week1.RandomValueGenerator$$anonfun$main$1.test$1(week1.RandomValueGe
                                                  //| nerator.scala:62)
                                                  //| 	at week1.RandomValueGenerator$$anonfun$main$1.apply$mcV$sp(week1.RandomV
                                                  //| alueGenerator.scala:69)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at week1.RandomValueGenerator$.main(week1.RandomValueGenerator.scala:3)
                                                  //| 
                                                  //| 	at week1.RandomValueGenerator.main(week1.RandomValueGenerator.scala)
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