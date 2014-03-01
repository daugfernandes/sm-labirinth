/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author david.paiva.fernandes@gmail.com
 * @author joaorodr84@gmail.com
 */
public class Move implements java.io.Serializable {

  private Direction _direction;
  private boolean _wasTried;

  public Move() {
    this._direction = Direction.Unknown;
    this._wasTried = false;
  }

  public Move(Direction direction) {
    this._direction = direction;
    this._wasTried = false;
  }

  public Direction getDirection() {
    return _direction;
  }

  public boolean getWasTried() {
    return _wasTried;
  }

  public void setDirection(Direction value) {
    this._direction = value;
  }

  public void setWasTried(boolean value) {
    _wasTried = true;
  }
  
  public int getIncX() {
      if (this._direction == Direction.East) return 1;
      else if (this._direction == Direction.West) return -1;
      else return 0;
  }
  
  public int getIncY() {
      if (this._direction == Direction.North) return -1;
      else if (this._direction == Direction.South) return 1;
      else return 0;
  }

}
