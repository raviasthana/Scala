package week1

/**
 * GUIDING PRINCIPLES FOR GOOD DESIGN
 * 
 * Name everything you can i.e break your program into smaller parts
 * so that the program is intelligible and readable - READIBILITY is
 * far more IMPORTANT than SHORT-NESS of the program
 * 
 * Put operations into natural scopes i.e. define methods which make
 * sense and make things explicit. e.g. change method within Move trait
 * as a move changes things so define a method "change" within Move
 * is a natural and GOOD thing to do
 * 
 * Keep degrees of freedom for future refinements 
 */

class Pouring(capacity: Vector[Int]) { //capacity - vector of all the glasses and it's capacity
  
  type State = Vector[Int]
  //initial state is all glasses are empty
  val initialState = capacity map ( x => 0 )
  
  //trait and case classes representing moves involved
  trait Move {
    //move changes things so it makes sense to define 
    //a method which indicates this principle from GOOD PROGRAM DESIGN
    //point of view
    def change(state: State): State
  }
  //empty an arbitrary glass
  case class Empty(glass: Int) extends Move{
    def change(state: State) = state updated (glass, 0) //updated doesn't destroy the old state, it just creates a new state
  }
  //fill an arbitrary glass
  case class Fill(glass: Int) extends Move{
    def change(state: State) = state updated (glass, capacity(glass))
  }
  //pour FROM an arbitrary glass TO an arbitrary glass
  case class Pour(from: Int, to: Int) extends Move{
    def change(state: State) = {
      val amount = state(from) min (capacity(to) - state(to))
      state updated (from, state(from) - amount) updated (to, state(to) + amount)
    }
  }
  
  //number of glasses at play
  val glasses = 0 until capacity.length
  
  //all possible moves given the number of glasses at play
  val moves = 
    (for (g <- glasses) yield Empty(g)) ++
    (for (g <- glasses) yield Fill(g)) ++
    (for (from <- glasses; to <- glasses if (from != to)) yield Pour(from,to))
  
  /*
   * class representing paths as a result of moves made
   * 
   * moves are in reverse chronological order i.e last move is first in the list
   * 
   * endState gets called a lot and each call is foldRight on history of all possible
   * paths. So its not very efficient to compute the end state as path gets longer and
   * and longer and state gets more complicated. 
   * It would be better to store the endState as part of the path than
   * to compute it and to provide it as a method of path class
   * 
   * endState is provided from outside so it's a val parameter
   */
  class Path(history: List[Move], val endState: State){
    //endState reached as a result of the last move
    //def endState: State = (history foldRight initialState)(_ change _)
    /*
    def endState(xs: List[Move]): State = trackState(history)       
    private def trackState(xs: List[Move]): State = xs match {
      case Nil => initialState
      case move :: xs1 => move change trackState(xs1)
    }*/
    //extend the current path with a new move
    def extend(move: Move) = new Path(move :: history, move change endState)
    override def toString = (history.reverse mkString " ") + "--> " + endState
  }
  
  val initialPath = new Path(Nil,initialState)
  
  /* 
  * extend the path with new moves and then extend the earlier
  * extended path with successive moves and so or and so forth
  * However, consider only those paths that have not already
  * been explored i.e. whose "end state" is not already contained
  * in previously considered paths
  */
  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] =
    if (paths.isEmpty) Stream.empty
    else { // generate all possible NEW(more) paths
      val more = for {
        path <- paths
        next <- moves map path.extend //for EACH path, generate the NEXT PATHS by extending the path with all possible moves
        if !(explored contains next.endState) //do NOT consider the next path if it's endState has already been explored 
      } yield next
      paths #:: from(more,explored ++ (more map (_.endState))) // evolution of paths from MORE and then MORE of MORE and so on and so forth
                                                               // and also add the endState of the new paths in explored state  
    }
  
  /* set of all possible paths
   * 
   * in the first set - set of initial path
   * in the second set(element) - set of all paths of length 1 that start with initial path
   * in the third set(element) - set of all paths of length 2 and so on until infinity
  */
  val pathSets = from(Set(initialPath),Set(initialState))
  
  /* solution to reach the "target" volume as an end state of a path
   * 
   * Go through all the path sets and select the path which has target
   * volume in it's end state 
   */
  def solution(target: Int): Stream[Path] = 
    for {
      pathSet <- pathSets
      path <- pathSet
      if (path.endState contains target)
    } yield path
}