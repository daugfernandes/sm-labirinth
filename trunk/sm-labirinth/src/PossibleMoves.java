/**
 * Class PossibleMoves
 * Defines the possible moves for a cell
 * @author David Fernandes <david.paiva.fernandes@gmail.com>
 * @author João Marques <joaorodr84@gmail.com>
 */

import java.util.ArrayList;
import java.util.List;

public class PossibleMoves implements java.io.Serializable {
  private static final long serialVersionUID = 14L;

  // List of possible moves
  List<Move> _moves;

  // Default constructor
  public PossibleMoves() {
    _moves = new ArrayList<Move>();
  }

  // Adds a move to the possible moves
  public boolean addMove(Move move) {
    // Checks if the move already exists
    for (Move m : _moves) {
      if (m.getDirection().equals(move.getDirection())) {
        return false;       // Can't have twice the same move
      }
    }
    _moves.add(move);
    return true;
  }

  // Gets the next possible move for the current cell
  public Move getNextPossibleMove() {
    for (Move m : _moves) {
      if (!m.getWasTried()) {
        return m;
      }
    }
    return null;
  }

  // Gets all the possible moves
  public List<Move> getMoves() {
    return _moves;
  }

  // Checks if there is already a move with the given direction
  public boolean containsMove(Direction dir) {
    for (Move m : _moves)
      if (m.getDirection() == dir) return true;
    return false;
  }
}