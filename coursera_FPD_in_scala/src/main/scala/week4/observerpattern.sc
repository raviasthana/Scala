package week4

/**
Good
========================================
1) Decouples views from state
2) Allows to have a varying number of views of a given state
3) Simple to setup

Bad
========================================
1) Forces imperative style, since handlers are Unit-typed
2) Many moving parts that need to be co-ordinated
3) Concurrency makes things more complicated
	- if a view subscribe to two models and if both model updates
		get pulished to the view at the same time then "Race condition"
		may occur
4) View are still tightly bound to one state; view update
happens immediately
	- e.g. view state changes when model state changes
		it would have been better to have more loose coupling

To quantify (from an Adobe presentation in 2008)
------------------------------------------------
1) 1/3rd of the code in Adobe's desktop applications is devoted
to "event handling"
2) 1/2 of the bugs are found in this code.


How to Improve?
===============
1) Functional Reactive Programming (Functional view of events as apposed to imperative)
2) Abstracting over events and evenstreams with Futures and Observables
3) Handling concurrency using Actors

*/

object observerpattern {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val a = new BankAccount                         //> a  : week4.BankAccount = week4.BankAccount@edf4efb
  val b = new BankAccount                         //> b  : week4.BankAccount = week4.BankAccount@2f7a2457
  
  val c = new Consolidator(List(a,b))             //> c  : week4.Consolidator = week4.Consolidator@4909b8da
  c.totalBalance                                  //> res0: Int = 0
  
  a.deposit(20)
  c.totalBalance                                  //> res1: Int = 20
  
  b.deposit(30)
  c.totalBalance                                  //> res2: Int = 50
}

trait Publisher {

	private var subscribers: Set[Subscriber] = Set()
	
	def subscribe(subscriber: Subscriber): Unit =
		subscribers += subscriber
		
	def unsubscribe(subscriber: Subscriber): Unit =
		subscribers -= subscriber
		
	def publish(): Unit =
		subscribers.foreach(_.handler(this))
}

trait Subscriber {
	def handler(publisher: Publisher)
}

class BankAccount extends Publisher {
  private var balance = 0
  
  def currentBalance: Int = balance
  
  def deposit(amount: Int): Unit = {
    if(amount > 0) balance = balance + amount
    publish()
  }
  
  def withdraw(amount: Int): Unit =
    if(0 < amount && amount <= balance){
      balance = balance - amount
      publish()
    }else throw new Error("insufficient funds")
}

class Consolidator(observed: List[BankAccount]) extends Subscriber {
	observed.foreach(_.subscribe(this))
	
	private var total: Int = _ //_ means variable is initially un-initialized
	compute() //compute is called to initialize total
	
	private def compute() =
		total = observed.map(_.currentBalance).sum
	
	def handler(pub: Publisher) = compute()
	
	def totalBalance = total
}