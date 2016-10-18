package week1

object countChange {

  def countChange(money: Int, coins: List[Int]): Int = {
    def loop(rem: Int, coins: List[Int]): Int = {
      
      0
    }
    0
  }                                               //> countChange: (money: Int, coins: List[Int])Int
	/*
  def countChange(money: Int, coins: List[Int]) = {
  	var count = 0
  	def loop(hCoin: Int, tCoins: List[Int]): Int = {
  	  if(money % hCoin == 0){
  	    println("1 - > " + money + " % " + hCoin + " == [" + (money % hCoin) + "]")
  	    count += 1
  	  }
  	  var j = 1
  	  for(i <- tCoins){
    		j = 1
    		while(money - (i * j) > 0){
    	    if((money - (i * j)) % hCoin == 0){
    	    	println("2- > (" + money + " - (" + i + " * " + j + ")) % " + hCoin + " == [" + (money - (i * j)) % hCoin + "]")
    	  	  count += 1
    	  	}//end if
    	  	j += 1
    		}//end while
  	  }//end for
  	  if(!tCoins.isEmpty) eLoop(tCoins.head,tCoins.tail)
  	  
  	  def eLoop(thCoin: Int, rtCoins: List[Int]): Int = {
  	    var j = 0
  	    var k = 0
  	    for(i <- rtCoins){
    		  j = 0
    		  k = 0
    		  while(money - ((thCoin * (j + 1)) + (i * (k + 1))) > 0){
    		  	j += 1
    		  	k += 1
    		    while(money - ((thCoin * j) + (i * k)) > 0){
    		      if((money - ((thCoin * j) + (i * k))) % hCoin == 0){
    	    	    println("3 -> (" + money + " - (" + thCoin + " * " + j + " + (" + i + " * " + k + "))) % " + hCoin + " == [" + (money - ((thCoin * j) + (i * k))) % hCoin + "]")
    	  	      count += 1
    	  	    }//end if
    	  	    k += 1
    		    }//end while
    		    k = 0
    		  }//end while
  	    }//end for
  	    
  	    if(!rtCoins.isEmpty && rtCoins.size > 1) eLoop(rtCoins.head,rtCoins.tail)
  	    count
  	  }//end def eLoop
  	  
  	  if(!tCoins.isEmpty) loop(tCoins.head,tCoins.tail)
  	  count
  	}//end loop
  	
  	loop(coins.head,coins.tail)
  }
  
  countChange(20,List(1,2,4,5))
  
  //countChange(300,List(5,10,20))
  //countChange(300,List(5,10,20,50))
  //countChange(301,List(5,10,20,50,100,200,500))
  */
  
}