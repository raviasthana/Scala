package week1

object countChange {

  def countChange(money: Int, coins: List[Int]) = {
  	var count = 0
  	def loop(hCoin: Int, tCoins: List[Int]): Int = {
  	  if(money % hCoin == 0){
  	    println(money + " % " + hCoin + " == [" + (money % hCoin) + "]")
  	    count += 1
  	  }
  	  var j = 1
  	  for(i <- tCoins){
    		j = 1
    		while(money - (i * j) > 0){
    	    if((money - (i * j)) % hCoin == 0){
    	    	println("(" + money + " - (" + i + " * " + j + ")) % " + hCoin + " == [" + (money - (i * j)) % hCoin + "]")
    	  	  count += 1
    	  	}//end if
    	  	j += 1
    		}//end while
  	  }//end for
  	  if(!tCoins.isEmpty) eLoop(tCoins.head,tCoins.tail)
  	  
  	  def eLoop(thCoin: Int, rtCoins: List[Int]): Int = {
  	    var j = 1
  	    var k = 1
  	    for(i <- rtCoins){
    		  j = 1
    		  k = 1
    		  while(money - ((thCoin * j) + (i * k)) > 0){
    	      if((money - ((thCoin * j) + (i * k))) % hCoin == 0){
    	    	  println("(" + money + " - (" + thCoin + " * " + j + " + (" + i + " * " + k + "))) % " + hCoin + " == [" + (money - ((thCoin * j) + (i * k))) % hCoin + "]")
    	  	    count += 1
    	  	  }//end if
    	  	  j += 1
    		  }//end while
					
					j = 1
					k += 1
					println("thCoin: " + thCoin + ", j = " + j + ", i = " + i + ", k = " + k)
    		  while(money - ((thCoin * j) + (i * k)) > 0){
    	      if((money - ((thCoin * j) + (i * k))) % hCoin == 0){
    	    	  println("(" + money + " - (" + thCoin + " * " + j + " + (" + i + " * " + k + "))) % " + hCoin + " == [" + (money - ((thCoin * j) + (i * k))) % hCoin + "]")
    	  	    count += 1
    	  	  }//end if
    	  	  k += 1
    		  }//end while
  	    }//end for
  	    
  	    if(!rtCoins.isEmpty && rtCoins.size > 1) eLoop(rtCoins.head,rtCoins.tail)
  	    count
  	  }//end def eLoop
  	  
  	  if(!tCoins.isEmpty) loop(tCoins.head,tCoins.tail)
  	  count
  	}//end loop
  	
  	loop(coins.head,coins.tail)
  }                                               //> countChange: (money: Int, coins: List[Int])Int
  
  //countChange(10,List(1,2,3,5))
  countChange(300,List(5,10))                     //> 300 % 5 == [0]
                                                  //| (300 - (10 * 1)) % 5 == [0]
                                                  //| (300 - (10 * 2)) % 5 == [0]
                                                  //| (300 - (10 * 3)) % 5 == [0]
                                                  //| (300 - (10 * 4)) % 5 == [0]
                                                  //| (300 - (10 * 5)) % 5 == [0]
                                                  //| (300 - (10 * 6)) % 5 == [0]
                                                  //| (300 - (10 * 7)) % 5 == [0]
                                                  //| (300 - (10 * 8)) % 5 == [0]
                                                  //| (300 - (10 * 9)) % 5 == [0]
                                                  //| (300 - (10 * 10)) % 5 == [0]
                                                  //| (300 - (10 * 11)) % 5 == [0]
                                                  //| (300 - (10 * 12)) % 5 == [0]
                                                  //| (300 - (10 * 13)) % 5 == [0]
                                                  //| (300 - (10 * 14)) % 5 == [0]
                                                  //| (300 - (10 * 15)) % 5 == [0]
                                                  //| (300 - (10 * 16)) % 5 == [0]
                                                  //| (300 - (10 * 17)) % 5 == [0]
                                                  //| (300 - (10 * 18)) % 5 == [0]
                                                  //| (300 - (10 * 19)) % 5 == [0]
                                                  //| (300 - (10 * 20)) % 5 == [0]
                                                  //| (300 - (10 * 21)) % 5 == [0]
                                                  //| (300 - (10 * 22)) % 5 == [0]
                                                  //| (300 - (10 * 23)) % 5 == [0]
                                                  //| (300 - (10 * 24)) % 5 == [0]
                                                  //| (300 - (10 * 25)) % 5 == [0]
                                                  //| (300 - (10 * 26)) % 5 == [0]
                                                  //| (300 - (10 * 27)) % 5 == [0]
                                                  //| (300 - (10 * 28)) % 5 == [0]
                                                  //| (300 - (10 * 29)) % 5 == [0]
                                                  //| 300 % 10 == [0]
                                                  //| res0: Int = 31
  //countChange(300,List(5,10,20,50,100,200,500))
  
}