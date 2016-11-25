package week1.assignment.recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if(c == 0 || r == 0 || r == 1 || c == r) 1
    else pascal(c-1,r-1) + pascal(c,r-1)
  }
  
  /**
   * Exercise 2
   */
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
  }                                               //> balance: (chars: List[Char])Boolean
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      if(money == 0) 1
      else if(money > 0 && !coins.isEmpty)
        countChange(money - coins.head,coins) + countChange(money,coins.tail)
      else 0
    }
  }
