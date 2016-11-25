package week6

//In Sclala, maps are special in that they are iterable as well as functions.
object maps {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def romanNumerals = Map("I" -> 1, "V" -> 5)     //> romanNumerals: => scala.collection.immutable.Map[String,Int]
  def capitalOfCountry = Map("US" -> "Washington", "Switzerland" -> "Bern")
                                                  //> capitalOfCountry: => scala.collection.immutable.Map[String,String]

	//Class Map[Key, Value] also extends the function type Key => Value, so maps
	//can be used everywhere functions can. In particular, maps can be applied to key arguments:
	capitalOfCountry("US") //used as function //> res0: String = Washington
	//capitalOfCountry("Andorra") //this results in NoSuchElementException
	capitalOfCountry get "Andorra"            //> res1: Option[String] = None
	capitalOfCountry get "US"                 //> res2: Option[String] = Some(Washington)
	
	//Options are defined as "case classes", so they can be decomposed using pattern matching
	def showCapital(country: String) = capitalOfCountry.get(country) match {
			case Some(capital) => capital
			case None => "missing data"
	}                                         //> showCapital: (country: String)String
	
	showCapital("US")                         //> res3: String = Washington
	showCapital("Andorra")                    //> res4: String = missing data
	
	val fruit = List("apple", "pear", "orange", "pineapple")
                                                  //> fruit  : List[String] = List(apple, pear, orange, pineapple)
  //SQL type orderBy on a collection can be expressed by 'sortWith' and 'sorted'
	fruit sortWith (_.length < _.length)      //> res5: List[String] = List(pear, apple, orange, pineapple)
	fruit.sorted                              //> res6: List[String] = List(apple, orange, pear, pineapple)
	
	//groupBy partitions a collection into a map of collections according
	//to a discriminator funtion f
	fruit groupBy (_.head)                    //> res7: scala.collection.immutable.Map[Char,List[String]] = Map(p -> List(pea
                                                  //| r, pineapple), a -> List(apple), o -> List(orange))
                                                  
	//Map works by applying a funtion to each element in the list
	val l = List(1,2,3,4,5)                   //> l  : List[Int] = List(1, 2, 3, 4, 5)
	l.map ( x => x*2 )                        //> res8: List[Int] = List(2, 4, 6, 8, 10)
	
	//to return a sequence or list from the function, for example an Option
	def f(x: Int) = if(x > 2) Some(x) else None
                                                  //> f: (x: Int)Option[Int]
	l.map ( x => f(x) )                       //> res9: List[Option[Int]] = List(None, None, Some(3), Some(4), Some(5))
	
	//flatMap works by applying a function that returns a sequence for each element in the list
	//and then flattening the list into the original list
	def g(v: Int) = List(v-1,v,v+1)           //> g: (v: Int)List[Int]
	l.map(x => g(x))                          //> res10: List[List[Int]] = List(List(0, 1, 2), List(1, 2, 3), List(2, 3, 4), 
                                                  //| List(3, 4, 5), List(4, 5, 6))
  l.flatMap(x => g(x))                            //> res11: List[Int] = List(0, 1, 2, 1, 2, 3, 2, 3, 4, 3, 4, 5, 4, 5, 6)
	
	//flatMap can be used for built in Option class because and Option can be considered
	//a sequence that is either empty or has one item
	l.flatMap(x => f(x))                      //> res12: List[Int] = List(3, 4, 5)
	
	//A map can be implemented in a number of different ways, but regardless how it is implemented
	//it can be thought of as a sequence of Tuples, where a tuple is a pair of items, the key and value
	val m = Map(1 -> 2, 2 -> 4, 3 -> 6)       //> m  : scala.collection.immutable.Map[Int,Int] = Map(1 -> 2, 2 -> 4, 3 -> 6)
                                                  //| 
  m.toList                                        //> res13: List[(Int, Int)] = List((1,2), (2,4), (3,6))
  
  //we can access a tuple by accessing the inner variables _1 and _2
  val t = (1,2)                                   //> t  : (Int, Int) = (1,2)
  t._1                                            //> res14: Int = 1
  t._2                                            //> res15: Int = 2
  
  //To apply a funnction to the tuple, but only to the values and not the key
  m.mapValues(v => v*2)                           //> res16: scala.collection.immutable.Map[Int,Int] = Map(1 -> 4, 2 -> 8, 3 -> 1
                                                  //| 2)
  m.mapValues ( x => f(x) )                       //> res17: scala.collection.immutable.Map[Int,Option[Int]] = Map(1 -> None, 2 -
                                                  //| > Some(4), 3 -> Some(6))
  //using flatMap
  //List returned when value of the tuple passed
  m.flatMap(e => List(e._2))                      //> res18: scala.collection.immutable.Iterable[Int] = List(2, 4, 6)
  //Map returned if tupe is passed
  m.flatMap(e => List(e))                         //> res19: scala.collection.immutable.Map[Int,Int] = Map(1 -> 2, 2 -> 4, 3 -> 6
                                                  //| )
	//using Options with flatMap
	def h(k:Int, v:Int) = if(v > 2) Some(k -> v) else None
                                                  //> h: (k: Int, v: Int)Option[(Int, Int)]
	//Here is how to use it with flatMap
	//#1
	m.flatMap(e => h(e._1,e._2))              //> res20: scala.collection.immutable.Map[Int,Int] = Map(2 -> 4, 3 -> 6)
	//#2 - using matching pattern - NOTICE '{' usage i.e indicating a function block and not a parameter
	m.flatMap {case (k,v) => h(k,v)}          //> res21: scala.collection.immutable.Map[Int,Int] = Map(2 -> 4, 3 -> 6)
	
	//working with Option using filter
	//#1
	m.filter(e => f(e._2) != None)            //> res22: scala.collection.immutable.Map[Int,Int] = Map(2 -> 4, 3 -> 6)
	//#2
	m.filter {case (k,v) => f(v) != None}     //> res23: scala.collection.immutable.Map[Int,Int] = Map(2 -> 4, 3 -> 6)
	//#3
	m.filter {case (k,v) => f(v).isDefined}   //> res24: scala.collection.immutable.Map[Int,Int] = Map(2 -> 4, 3 -> 6)
}