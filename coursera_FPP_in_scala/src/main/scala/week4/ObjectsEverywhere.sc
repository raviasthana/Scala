package week4

object ObjectsEverywhere {
  println("Objects Everywhere")                   //> Objects Everywhere
}

// class representing natural numbers
// i.e. positive integers and NOT negative integers
// e.g. 0,1,2....100...and so on
// this is called "peano numbers"
abstract class Nat {
  def isZero: scala.Boolean
  def predecessor: Nat
  def successor: Nat = new Succ(this)
  def + (that: Nat): Nat
  def - (that: Nat): Nat
}

object Zero extends Nat {
  def isZero = true
  def predecessor: Nat = throw new Error("predecessor on Zero called!!!")
  //def successor: Nat = new Succ(this)
  def + (that: Nat): Nat = that
  def - (that: Nat): Nat = {
    if(that.isZero) this
    else throw new Error("minus on Zero called!!!")
  }
}

//class representing successor of given Nat n
class Succ(n: Nat) extends Nat {
  def isZero = false
  def predecessor: Nat = n
  //def successor: Nat = new Succ(this) //successor of given Nat n
  def + (that: Nat): Nat = new Succ(n + that)
  def - (that: Nat): Nat = if(that.isZero) this else n - that.predecessor
}