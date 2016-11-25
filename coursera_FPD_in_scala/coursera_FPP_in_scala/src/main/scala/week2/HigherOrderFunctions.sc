package week2

object HigherOrderFunctions {
  
  def sum(f: Int => Int, a: Int, b: Int): Int = {
    if(a > b) 0
    else f(a) + sum(f,a+1,b)
  }                                               //> sum: (f: Int => Int, a: Int, b: Int)Int
  
  def cube(x: Int) = x * x * x                    //> cube: (x: Int)Int
  
  def sumCubes(a: Int, b: Int) = sum(cube,a,b)    //> sumCubes: (a: Int, b: Int)Int
  
  sumCubes(2,3)                                   //> res0: Int = 35
  
  //anonymous function usage
  def sumCubes2(a: Int, b:Int) = sum((x: Int) => x * x * x,a,b)
                                                  //> sumCubes2: (a: Int, b: Int)Int
  //anonymous function with type inferred
  def sumInts(a: Int, b: Int) = sum(x => x,a,b)   //> sumInts: (a: Int, b: Int)Int
  sumCubes2(3,4)                                  //> res1: Int = 91
  sumInts(2,3)                                    //> res2: Int = 5
  
  //tail recursive version
  def sum2(f:Int => Int)(a:Int, b:Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if(a > b) acc
      else loop(a+1, f(a) + acc)
    }
    loop(a,0)
  }                                               //> sum2: (f: Int => Int)(a: Int, b: Int)Int
  
  sum(x => x * x, 3, 5)                           //> res3: Int = 50
}