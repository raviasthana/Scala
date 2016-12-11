package week1

object test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
    
  val f: String => String = {case "ping" => "pong"}
                                                  //> f  : String => String = <function1>
  f("ping")                                       //> res0: String = pong
  //f("abc")
 
  val f2: PartialFunction[String,String] = {case "ping" => "pong"}
                                                  //> f2  : PartialFunction[String,String] = <function1>
  f2.isDefinedAt("ping")                          //> res1: Boolean = true
  f2.isDefinedAt("abc")                           //> res2: Boolean = false
  
  val f3: PartialFunction[List[Int], String] = {
  	case Nil => "one"
  	case x :: y :: rest => "two"
  }                                               //> f3  : PartialFunction[List[Int],String] = <function1>
  
  f3.isDefinedAt(List(1,2,3))                     //> res3: Boolean = true

  val g: PartialFunction[List[Int], String] = {
    case Nil => "one"
    case x :: rest =>
			rest match {
				case Nil => "two"
			}
  }                                               //> g  : PartialFunction[List[Int],String] = <function1>
  
  //isDefinedAt guarantees for outer case matches and not nested case matches
	g.isDefinedAt(List(1,2,3))                //> res4: Boolean = true
	
	val books: Set[Book] = Set(
	//val books: List[Book] = List(
		Book(title		= "Structure and Interpretation of Computer Programs",
				authors		= List("Abelson, Herald", "Sussman, Gerald J.")),
		Book(title		= "Introduction to Functional Programming",
				authors		= List("Bird, Richard", "Wadler, Phil")),
		Book(title		= "Effective Java",
				authors		= List("Bloch, Joshua")),
		Book(title		= "Effective Java2",
				authors		= List("Bloch, Joshua")),
		Book(title		= "Java Puzzlers",
				authors		= List("Bloch, Joshua", "Gafter, Neal")),
		Book(title		= "Programming in Scala",
				authors		= List("Odersky, Martin", "Spoon, Lex", "Venners, Bill"))
	)                                         //> books  : Set[week1.Book] = Set(Book(Programming in Scala,List(Odersky, Mart
                                                  //| in, Spoon, Lex, Venners, Bill)), Book(Effective Java2,List(Bloch, Joshua)),
                                                  //|  Book(Effective Java,List(Bloch, Joshua)), Book(Introduction to Functional 
                                                  //| Programming,List(Bird, Richard, Wadler, Phil)), Book(Structure and Interpre
                                                  //| tation of Computer Programs,List(Abelson, Herald, Sussman, Gerald J.)), Boo
                                                  //| k(Java Puzzlers,List(Bloch, Joshua, Gafter, Neal)))
	for(b <- books; a <- b.authors if a startsWith "Bird,") yield b.title
                                                  //> res5: scala.collection.immutable.Set[String] = Set(Introduction to Function
                                                  //| al Programming)
	//for(b <- books if b.title indexOf "Program" >= 0) yield b.title
	/*
	for {
			b1 <- books
			b2 <- books
			//if b1 != b2
			if b1.title < b2.title
			a1 <- b1.authors
			a2 <- b2.authors
			if (a1 == a2)
		} yield a1
		*/
	
	
			
	//books flatMap ( b => for (a <- b.authors if a.startsWith("Bird") ) yield b.title )
	//books flatMap ( b => for (a <- b.authors withFilter(a => a.startsWith("Bird") )) yield b.title )
	books flatMap ( b => b.authors withFilter(a => a.startsWith("Bird") ) map (a => b.title) )
                                                  //> res6: scala.collection.immutable.Set[String] = Set(Introduction to Function
                                                  //| al Programming)
}

case class Book(title: String, authors: List[String])