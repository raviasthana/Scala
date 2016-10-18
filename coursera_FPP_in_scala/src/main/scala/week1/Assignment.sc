package week1

object Assignment {
  
  def moneyChanges(money: Int, coins: List[Int]) : Option[List[Seq[Int]]]= {
		var listOfChange=List[Seq[Int]]()
		def changeMoney(capacity: Int, changes: List[Int], listOfCoins: Option[Seq[Int]]): Int = {
  		if (capacity == 0) {
    		listOfChange = listOfCoins.get :: listOfChange
    		1
  		} else if (capacity < 0)
    		0
  		else if (changes.isEmpty && capacity >= 1)
    		0
  		else {
    		changeMoney(capacity, changes.tail, listOfCoins) +
    		changeMoney(capacity - changes.head, changes,
      	Some(changes.head +: listOfCoins.getOrElse(Seq())))
  		}
		}

		changeMoney(money, coins.sortWith(_.compareTo(_) < 0), None)
		Some(listOfChange)
	}                                         //> moneyChanges: (money: Int, coins: List[Int])Option[List[Seq[Int]]]
	
	moneyChanges(10,List(1,2,4,5))            //> res0: Option[List[Seq[Int]]] = Some(List(List(1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                                                  //|  List(2, 1, 1, 1, 1, 1, 1, 1, 1), List(2, 2, 1, 1, 1, 1, 1, 1), List(4, 1, 1
                                                  //| , 1, 1, 1, 1), List(5, 1, 1, 1, 1, 1), List(2, 2, 2, 1, 1, 1, 1), List(4, 2,
                                                  //|  1, 1, 1, 1), List(5, 2, 1, 1, 1), List(2, 2, 2, 2, 1, 1), List(4, 2, 2, 1, 
                                                  //| 1), List(4, 4, 1, 1), List(5, 2, 2, 1), List(5, 4, 1), List(2, 2, 2, 2, 2), 
                                                  //| List(4, 2, 2, 2), List(4, 4, 2), List(5, 5)))
	//moneyChanges(300,List(5,10,20,50,100,200,500))
	
  /*
  def pascal(c:Int, r:Int):Int = {
  	if(c == 0 || r == 0 || r == 1 || c == r) 1
  	else pascal(c-1,r-1) + pascal(c,r-1)
  }
  
  pascal(0,8)
  pascal(1,8)
  pascal(2,8)
  pascal(3,8)
  pascal(4,8)
  pascal(5,8)
  pascal(6,8)
  pascal(7,8)
  pascal(8,8)
  
  def balance(chars: List[Char]): Boolean = {
  	var count = 0
    def loop(c: Char, rest: List[Char]): Boolean = {
    		if(c == '(') count += 1
    		if(c == ')') count -= 1
    		if(rest.isEmpty || count < 0) count == 0
    		else loop(rest.head,rest.tail)
    }
    
	  if(chars.isEmpty) true
	  else loop(chars.head,chars.tail)
	}
  
  balance(":-)".toList)
  balance("())(".toList)
  balance("(if (zero? x) max (/ 1 x))".toList)
  balance("I told him (that it's not (yet) done).\n(But he wasn't listening)".toList)
  */
  
  def countChange(money: Int, coins: List[Int]): Int = {
  if(money == 0)
    1
  else if(money > 0 && !coins.isEmpty)
    countChange(money - coins.head, coins) + countChange(money, coins.tail)
  else
    0
  }                                               //> countChange: (money: Int, coins: List[Int])Int
  
  //countChange(300,List(5,10,20))
  //countChange(300,List(5,10,20,50,100,200,500))
  countChange(300,List(5,10,20,50))               //> res1: Int = 680
  //countChange(301,List(5,10,20,50,100,200,500))
  //countChange(10,List(1,2,5))
  //countChange(4,List(1,2,3))
  //countChange(10,List(2,5))
  //countChange(10,List(1,2,3,5))
  countChange(10,List(1,2,4,5))                   //> res2: Int = 17
  
  /*
  def loopDemo(nos: List[Int]){
	  nos.foreach(((i) => println(i)))
  }
  
  loopDemo(List(1,2,3,5))
  */
  
  /*
  pascal(0,0)
  
  pascal(0,1)
  pascal(1,1)
  
  pascal(0,2)
  pascal(1,2)
  pascal(2,2)
  
  pascal(0,3)
  pascal(1,3)
  pascal(2,3)
  pascal(3,3)
  
  pascal(0,4)
  pascal(1,4)
  pascal(2,4)
  pascal(3,4)
  pascal(4,4)
  */
}