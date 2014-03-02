/**
 * Class Cell
 * @author David Fernandes <david.paiva.fernandes@gmail.com>
 * @author João Marques <joaorodr84@gmail.com>
 */

public class Cell implements java.io.Serializable {

  private static final long serialVersionUID = 6L;

  private int _x;                           // X coordinate of the cell
  private int _y;                           // Y coordinate of the cell
  private boolean _isWall;                  // Indicates if the cell is a wall
  private PossibleMoves _possibleMoves;     // Possible moves starting from the current cell
  private boolean _wasTried;                // Indicates if the cell was already tried/visited

  // Default constructor
  public Cell() {
    this(-1, -1, true);
  }

  // Constructor by arguments
  public Cell(int y, int x, boolean isWall) {
    this._x = x;
    this._y = y;
    this._possibleMoves = new PossibleMoves();
    this._isWall = isWall;
  }

  // Verifies if the cell was properly constructed
  public boolean inUse() {
    return (this._x >= 0 && this._y >= 0);
  }

  // Adds a possible move to the current cell
  public boolean addPossibleMove(Move move) {
    return _possibleMoves.addMove(move);
  }

  // Gets the next possible move
  public Move getNextPossibleMove() {
    return _possibleMoves.getNextPossibleMove();
  }

  // PossibleMoves getter
  public PossibleMoves getPossibleMoves() {
    return _possibleMoves;
  }

  // WasTried getter
  public boolean getWasTried() {
    return _wasTried;
  }

  // WasTried setter
  public void setWasTried() {
    _wasTried = true;
  }

  // IsWall setter / Sets IsWall to true when the cell is a wall
  public void SetIsWall() {
    _isWall = true;
  }

  // Sets IsWall to false when the cell is not a wall
  public void SetIsPathWay() {
    _isWall = false;
  }

  // Checks if the cell is a path way
  public boolean IsPathWay() {
    return !_isWall;
  }

  // Checks if the cell is a wall
  public boolean IsWall() {
    return _isWall;
  }

  // X coordinate getter
  public int getX() {
    return _x;
  }

  // Y coordinate getter
  public int getY() {
    return _y;
  }

  @Override
  // Converts the cell's coordinates to string type
  public String toString() {
    return String.format("Cell @(%d,%d)", this._y, this._x);
  }
}