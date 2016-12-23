package week4.assignment.calculator

import math.sqrt

object Polynomial {
  def computeDelta(a: Signal[Double], b: Signal[Double],
      c: Signal[Double]): Signal[Double] = {
    Signal(b()*b() - 4*a()*c())
  }

  def computeSolutions(a: Signal[Double], b: Signal[Double],
      c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = {
    
    Signal({
      delta() match {
        case 0 => Set(-b() / (2*a()))
        case d if (d > 0) =>
          Set( 
              (-b() + sqrt(delta())) / (2*a()), 
              (-b() - sqrt(delta())) / (2*a()) 
             )
        case _ => Set()
      }      
    })  
  }
  
}
