package week4

object ObjectsEverywhere {
  println("Objects Everywhere")                   //> Objects Everywhere
}

// class representing natural numbers
// i.e. positive integers and NOT negative integers
// e.g. 0,1,2....100...and so on
abstract class Nat {
  def isZero: scala.Boolean
  def predecessor: Nat
  def successor: Nat
  def + (that: Nat): Nat
  def - (that: Nat): Nat
}

object Zero extends Nat {
  def isZero = true
  def predecessor: Nat = throw new IllegalArgumentException("Zero is the smallest natural number")
  def successor: Nat = new Succ(this).successor
  def + (that: Nat): Nat = ???
  def - (that: Nat): Nat = ???
}

class Succ(n: Nat) extends Nat {
  def isZero = if(n == Zero) true else false
  def predecessor: Nat = ???
  def successor: Nat = ???
  def + (that: Nat): Nat = ???
  def - (that: Nat): Nat = ???
}