package week3.reactive

object circuitSimulation {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
	object sim extends Circuits with Parameters
	import sim._
	val in1, in2, sum, carry = new Wire       //> in1  : week3.reactive.circuitSimulation.sim.Wire = week3.reactive.Gates$Wire
                                                  //| @340f438e
                                                  //| in2  : week3.reactive.circuitSimulation.sim.Wire = week3.reactive.Gates$Wire
                                                  //| @30c7da1e
                                                  //| sum  : week3.reactive.circuitSimulation.sim.Wire = week3.reactive.Gates$Wire
                                                  //| @5b464ce8
                                                  //| carry  : week3.reactive.circuitSimulation.sim.Wire = week3.reactive.Gates$Wi
                                                  //| re@57829d67
	halfAdder(in1,in2,sum,carry)
	probe("sum",sum)                          //> sum 0 value = false
	probe("carry",carry)                      //> carry 0 value = false
	
	in1 setSignal true
	run()                                     //> *** simulation started, time = 0 ***
                                                  //| sum 8 value = true
	in2 setSignal true
	run()                                     //> *** simulation started, time = 8 ***
                                                  //| carry 11 value = true
                                                  //| sum 16 value = false
	in1 setSignal false
	run()                                     //> *** simulation started, time = 16 ***
                                                  //| carry 19 value = false
                                                  //| sum 24 value = true
}