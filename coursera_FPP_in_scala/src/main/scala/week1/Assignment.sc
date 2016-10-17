package week1

object Assignment {
  
  def pascal(c:Int, r:Int):Int = {
  	if(c == 0 || r == 0 || r == 1 || c == r) 1
  	else pascal(c-1,r-1) + pascal(c,r-1)
  }                                               //> pascal: (c: Int, r: Int)Int
  
  pascal(0,8)                                     //> res0: Int = 1
  pascal(1,8)                                     //> res1: Int = 8
  pascal(2,8)                                     //> res2: Int = 28
  pascal(3,8)                                     //> res3: Int = 56
  pascal(4,8)                                     //> res4: Int = 70
  pascal(5,8)                                     //> res5: Int = 56
  pascal(6,8)                                     //> res6: Int = 28
  pascal(7,8)                                     //> res7: Int = 8
  pascal(8,8)                                     //> res8: Int = 1
  
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
	}                                         //> balance: (chars: List[Char])Boolean
  
  balance(":-)".toList)                           //> res9: Boolean = false
  balance("())(".toList)                          //> res10: Boolean = false
  balance("(if (zero? x) max (/ 1 x))".toList)    //> res11: Boolean = true
  balance("I told him (that it's not (yet) done).\n(But he wasn't listening)".toList)
                                                  //> res12: Boolean = true
  
  def countChange(money: Int, coins: List[Int]): Int = {
  if(money == 0)
    1
  else if(money > 0 && !coins.isEmpty)
    countChange(money - coins.head, coins) + countChange(money, coins.tail)
  else
    0
  }                                               //> countChange: (money: Int, coins: List[Int])Int
  
  countChange(300,List(5,10))                     //> res13: Int = 31
  countChange(300,List(5,10,20,50,100,200,500))   //> res14: Int = 1022
  countChange(301,List(5,10,20,50,100,200,500))   //> res15: Int = 0
  countChange(10,List(1,2,5))                     //> res16: Int = 10
  countChange(4,List(1,2,3))                      //> res17: Int = 4
  countChange(10,List(2,5))                       //> res18: Int = 2
  countChange(10,List(1,2,3,5))                   //> res19: Int = 20
  
  def loopDemo(nos: List[Int]){
	  nos.foreach(((i) => println(i)))
  }                                               //> loopDemo: (nos: List[Int])Unit
  
  loopDemo(List(1,2,3,5))                         //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 5
  
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