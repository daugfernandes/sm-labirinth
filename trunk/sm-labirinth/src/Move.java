/**
 * Class Move
 * Defines a move
 * @author David Fernandes <david.paiva.fernandes@gmail.com>
 * @author João Marques <joaorodr84@gmail.com>
 */

public class Move implements java.io.Serializable {
  private static final long serialVersionUID = 8L;

  private Direction _direction;
  private boolean _wasTried;

  // Default constructor
  public Move() {
    this._direction = Direction.Unknown;
    this._wasTried = false;
  }

  // Constructor by argument
  public Move(Direction direction) {
    this._direction = direction;
    this._wasTried = false;
  }

  // Direction getter
  public Direction getDirection() {
    return _direction;
  }

  // WasTried getter
  public boolean getWasTried() {
    return _wasTried;
  }

  // Direction setter
  public void setDirection(Direction value) {
    this._direction = value;
  }

  // WasTried setter
  public void setWasTried(boolean value) {
    _wasTried = true;
  }
  
  // Gets the incrementation of X given a direction to follow
  public int getIncX() {
      if (this._direction == Direction.East) {
        return 1;
      } else if (this._direction == Direction.West) {
        return -1;
      } else {
        return 0;
      }
  }
  
  // Gets the incrementation of Y given a direction to follow
  public int getIncY() {
      if (this._direction == Direction.North) {
        return -1;
      } else if (this._direction == Direction.South) {
        return 1;
      } else {
        return 0;
      }
  }
}