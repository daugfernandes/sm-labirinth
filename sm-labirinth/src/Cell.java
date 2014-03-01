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
public class Cell implements java.io.Serializable {
  private static final long serialVersionUID = 1L;

  private int _x;
  private int _y;
  private boolean _isWall;
  private PossibleMoves _possibleMoves;

  public Cell() {
    this(-1, -1, true);
  }

  public Cell(int x, int y, boolean isWall) {
    this._x = x;
    this._y = y;
    this._possibleMoves = new PossibleMoves();
    this._isWall = isWall;
  }

  // just a way to tell the cell was properly constructed
  public boolean inUse() {
    return (this._x >= 0 && this._y >= 0);
  }

  public boolean addPossibleMove(Move move) {
    return _possibleMoves.addMove(move);
  }

  public Move getNextPossibleMove() {
    return _possibleMoves.getNextPossibleMove();
  }

  public PossibleMoves getPossibleMoves() {
    return _possibleMoves;
  }

  public void SetIsWall() {
    _isWall = true;
  }

  public void SetIsPathWay() {
    _isWall = false;
  }

  public boolean IsPathWay() {
    return !_isWall;
  }

  public boolean IsWall() {
    return _isWall;
  }

  public int getX() {
    return _x;
  }

  public int getY() {
    return _y;
  }

  @Override
  public String toString() {
    return String.format("Cell @(%d,%d)", this._x, this._y);
  }

}
