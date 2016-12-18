package week4

import week4.frp.Signal
/*
Reactive programming is about reacting to sequences of events that happen in time

Functional view: Aggregate an event sequence into a signal
	1) A signal is a value that changes over time
	2) It is represented as a function from time to the value domain.
	3) Instead of proagating updates to mutable state, we define new
		 signals in terms of existing ones.
		 
	Event-based view:
	-	MouseMoved(toPos: Position)
	
	is fired.
	
	FRP view:
	A signal,
		mousePosition: Signal[Position] (A function from time to value domain)
		-	which at any point in time represents the current mouse position
		
	FRP standalone languages and libraries - Flapjax, Elm, Bacom.js React4j(Java library)
	
		
*/
object FunctionalReactiveProgramming {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def consolidated(accts: List[BankAccountFRP]): Signal[Int] =
	Signal(accts.map(_.balance()).sum)        //> consolidated: (accts: List[week4.BankAccountFRP])week4.frp.Signal[Int]
	
	val a = new BankAccountFRP()              //> a  : week4.BankAccountFRP = week4.BankAccountFRP@7f690630
	val b = new BankAccountFRP()              //> b  : week4.BankAccountFRP = week4.BankAccountFRP@edf4efb
	
	val c = consolidated(List(a,b))           //> c  : week4.frp.Signal[Int] = week4.frp.Signal@6615435c
	c()                                       //> res0: Int = 0
	
	a deposit 20
	c()                                       //> res1: Int = 0
	
	b deposit 30
	c()                                       //> res2: Int = 0
	
	val xchange = Signal(268.00)              //> xchange  : week4.frp.Signal[Double] = week4.frp.Signal@34b7bfc0
	val inDollar = Signal(c() * xchange())    //> inDollar  : week4.frp.Signal[Double] = week4.frp.Signal@366e2eef
	inDollar()                                //> res3: Double = 0.0
}