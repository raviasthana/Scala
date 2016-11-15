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
}