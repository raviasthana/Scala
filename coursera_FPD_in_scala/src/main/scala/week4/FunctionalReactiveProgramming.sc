package week4

object FunctionalReactiveProgramming {
  println("Welcome to the Scala worksheet")
}

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

object accounts {
	def consolidated(accts: List[BankAccountFRP]): Signal[Int] =
		Signal(accts.map(_.balance()).sum)
	
	val a = new BankAccountFRP()
	val b = new BankAccountFRP()
	
	val c = consolidated(List(a,b))
	c()
	
	a deposit 20
	c()
	
	b deposit 30
	c()
	
	val xchange = Signal(246.00)
	val inDollar = Signal(c() * xchange())
	inDollar()
}