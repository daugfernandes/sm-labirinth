/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.labirinth;

/**
 *
 * @author david.paiva.fernandes@gmail.com
 * @author joaorodr84@gmail.com
 */
public class Cell {

    int _x;
    int _y;
    PossibleMoves _possibleMoves;

    public Cell() {
        this(-1, -1);
    }

    public Cell(int x, int y) {
        this._x = x;
        this._y = y;
        this._possibleMoves = new PossibleMoves();
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
    
    /**
     * Makes a next (forward) move
     */
    public void makeNextMove() {
        
        Move nextMove = this.getNextPossibleMove();
                
        if (nextMove._direction == Direction.North) this._y++;
        else if (nextMove._direction == Direction.South) this._y--;
        else if (nextMove._direction == Direction.West) this._x--;
        else if (nextMove._direction == Direction.East) this._x++;
    }
    
    /**
     * Moves to an other cell (used in backtracking)
     * @param other 
     */
    public void moveTo(Cell other) {
        this._x = other._x;
        this._y = other._y;
    }
}
