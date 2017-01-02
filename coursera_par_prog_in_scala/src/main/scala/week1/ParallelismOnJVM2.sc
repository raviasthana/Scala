package week1

  /*
  Deadlock is a scenario in which two are more threads compete for resources
  (such as monitor ownership), and "wait" for "other" to finish without releasing
  the already acquired resources
  */
  /*
  Java Memory Model - memory model for the Java Run Time (JVM)
  -----------------
  
  Memory model is a set of rules that describes how threads interact when accessing
  shared memory
  
  - small subset of rules (just 2 here)
  
  1) Two threads writing to separate locations in memory do not need synchronization
  2) A thread X that calls join on another thread Y is guaranteed to see all writes
  by thread Y after join returns.
  
  */
object ParallelismOnJVM2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def startThread(a: Account, b: Account, n: Int) = {
    val t = new Thread {
      override def run() {
        for(i <- 0 until n){
          a.transfer(b, 1)
        }
      }
    }
    t.start()
    t
  }                                               //> startThread: (a: week1.Account, b: week1.Account, n: Int)Thread
  
  val a1 = new Account(50000)                     //> a1  : week1.Account = week1.Account@5b464ce8
  val a2 = new Account(70000)                     //> a2  : week1.Account = week1.Account@57829d67
  
  val t = startThread(a1, a2, 15000)              //> t  : Thread = Thread[Thread-0,5,main]
  val s = startThread(a2, a1, 15000)              //> s  : Thread = Thread[Thread-1,5,main]-
  t.join() //deadlock @ t.join
  s.join()
}

class Account(private var amount: Int = 0){

  private val x = new AnyRef {}
  private var uidCount = 0L
  
  def getUniqueId(): Long = x.synchronized {
  	uidCount = uidCount + 1
  	uidCount
  }
  
  val uuid = getUniqueId()
  
  private def lockAndTransfer(target: Account, n: Int) = {
    this.synchronized {
      target.synchronized {
        this.amount -= n
        target.amount += n
      }
    }
  }

  def transfer(target: Account, n: Int) =
	  if(this.uuid < target.uuid) this.lockAndTransfer(target, n)
	  else target.lockAndTransfer(this, -n)
	
	/*
  def transfer(target: Account, tAmount: Int) =
    this.synchronized {
      target.synchronized {
        this.amount -= tAmount
        target.amount += tAmount
      }
    }
   */
}