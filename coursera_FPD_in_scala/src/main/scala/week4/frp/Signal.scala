package week4.frp

import scala.util.DynamicVariable

/**
 * Each Signal maintains
 * - it's current value
 * - the current expression that defines the signal value
 * - a set of observers: the other signals that depend on it's value
 * 
 * If the Signal changes then all the observers need to be re-evaluated
 * 
 * 
 * A Signals current value can change when
 * - someone calls update operation on a Var, or
 * - the value of a dependent signal changes
 */
class Signal[T](expr: => T) {
  import Signal._
  /*
   * what happens if we try to evaluate several signal expressions in parallel?
   * because caller is global shared state, it might result in race condition if
   * multiple threads update caller at the same time.
   * so the caller signal may become 'garbled' by concurrent updates
   *  
   * synchronization can be used to get around the problem of concurrent acces 
   * of the global state. However, synchronization has it's own problems
   * - It blocks threads, can be slow and can lead to deadlocks
   * 
   * Another solution is to replace global state by thread-local state.
   * thread-local state means that each thread accesses a separate copy of a variable.
   *  
   * It is supported in Scala through scala.util.DynamicVariable
   * 
   * ThreadLocal state still comes with its own share of disadvantages
   * - 	it's imperative nature often produces hidden dependencies which are
   * 		hard to manage
   * -	it's implementation on the JDK involves a global hash table lookup,
   * 		which can be a performance problem (JDK stores all the thread local 
   * 		variable of a thread to a global hash table so every access to thread
   *    local variable of a thread	involves a global hashtable lookup)
   * -	it does not play well in situations where threads are multiplexed
   * 		between several tasks (like worker threads, because you cannot take
   * 		thread local state from one thread to other) 
   */
  //private val caller = new StackableVariable[Signal[_]](NoSignal)
  //API of DynamicVariable matches our implementation of StackableVariable
  //DynamicVariable gives us thread local state
  private val caller = new DynamicVariable[Signal[_]](NoSignal)
  private var myExpr: () => T = _ //uninitialized
  private var myValue: T = _
  private var observers: Set[Signal[_]] = Set()
  update(expr)
  
  protected def update(expr: => T): Unit = {
    myExpr = () => expr
    computeValue()
  }
  
  protected def computeValue(): Unit = {
    val newValue = caller.withValue(this)(myExpr())
    if(newValue != myValue){
      myValue = newValue
      val obs = observers
      observers = Set()
      obs.foreach(_.computeValue())
    }
  }
  
  def apply(): T = {
    observers += caller.value
    assert(!caller.value.observers.contains(this), "cyclic signal definition")
    myValue
  }
}

object Signal{
  def apply[T](expr: => T) = new Signal(expr)
}

object NoSignal extends Signal[Nothing](???){
  //computeValue needs to be disabled for NoSignal because we cannot
  //evaluate an expression of type Nothing
  override def computeValue = ()
}