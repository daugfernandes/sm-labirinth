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
    
}
