package week1

object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(72); 
  println("Welcome to the Scala worksheet");$skip(57); 
    
  val f: String => String = {case "ping" => "pong"};System.out.println("""f  : String => String = """ + $show(f ));$skip(12); val res$0 = 
  f("ping");System.out.println("""res0: String = """ + $show(res$0));$skip(83); 
  //f("abc")
  
  val f2: PartialFunction[String,String] = {case "ping" => "pong"};System.out.println("""f2  : PartialFunction[String,String] = """ + $show(f2 ));$skip(25); val res$1 = 
  f2.isDefinedAt("ping");System.out.println("""res1: Boolean = """ + $show(res$1));$skip(24); val res$2 = 
  f2.isDefinedAt("abc");System.out.println("""res2: Boolean = """ + $show(res$2));$skip(109); 
  
  val f3: PartialFunction[List[Int], String] = {
  	case Nil => "one"
  	case x :: y :: rest => "two"
  };System.out.println("""f3  : PartialFunction[List[Int],String] = """ + $show(f3 ));$skip(33); val res$3 = 
  
  f3.isDefinedAt(List(1,2,3));System.out.println("""res3: Boolean = """ + $show(res$3));$skip(141); 

  val g: PartialFunction[List[Int], String] = {
    case Nil => "one"
    case x :: rest =>
			rest match {
				case Nil => "two"
			}
  };System.out.println("""g  : PartialFunction[List[Int],String] = """ + $show(g ));$skip(109); val res$4 = 
  
  //isDefinedAt guarantees for outer case matches and not nested case matches
	g.isDefinedAt(List(1,2,3));System.out.println("""res4: Boolean = """ + $show(res$4));$skip(658); 
	
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
	);System.out.println("""books  : Set[week1.Book] = """ + $show(books ));$skip(71); val res$5 = 
	for(b <- books; a <- b.authors if a startsWith "Bird,") yield b.title;System.out.println("""res5: scala.collection.immutable.Set[String] = """ + $show(res$5))}
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
	//books flatMap ( b => b.authors withFilter(a => a.startsWith("Bird") ) map (a => b.title) )
}

case class Book(title: String, authors: List[String])
