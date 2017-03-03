package week1

object ParallelismOnJVM1 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def main(){
  	val t = new HelloThread
  	val s = new HelloThread
  	t.start()
  	s.start()
  	t.join()
  	s.join()
  }                                               //> main: ()Unit
  
  //main()
  val td1 = new ThreadDemo1                       //> td1  : week1.ThreadDemo1 = week1.ThreadDemo1@5b464ce8
  td1.startThread()                               //> res0: Thread = Thread[Thread-0,5,main]
  
}

class HelloThread extends Thread {
	override def run() {
		println("Hello")
		println("World!")
	}
}

class ThreadDemo1{

  private var uidCount = 0L
  
  def getUniqueId(): Long = {
  	uidCount = uidCount + 1
  	uidCount
  }
  
  def startThread() = {
  	val t = new Thread{
  		override def run(){
  		  val uids = for (i <- 0 until 10) yield getUniqueId()
  		  println(uids)
  		}
  	}
  	t.start()
  	t
  }
}

class ThreadDemo2{

  private val x = new AnyRef {}
  private var uidCount = 0L
  
  def getUniqueId(): Long = x.synchronized {
  	uidCount = uidCount + 1
  	uidCount
  }
  
  def startThread() = {
  	val t = new Thread{
  		override def run(){
  		  val uids = for (i <- 0 until 10) yield getUniqueId()
  		  println(uids)
  		}
  	}
  	t.start()
  	t
  }
}