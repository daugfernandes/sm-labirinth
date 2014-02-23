/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.labirinth;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david.paiva.fernandes@gmail.com
 * @author joaorodr84@gmail.com
 */
public class PossibleMoves {

    List<Move> _moves;

    public PossibleMoves() {
        _moves = new ArrayList<>();
    }

    public boolean addMove(Move move) {
        // lets find out if this move's direction already exists
        for(Move m: _moves) {
            if(m.getDirection().equals(move.getDirection()))
                // oops! can't go there .... twice!!!!
                return false;
        }
        _moves.add(move);
        return true;
    }

    public Move getNextPossibleMove() {
        for (Move m : _moves) {
            if (!m.getWasTried()) {
                return m;
            }
        }
        return null;
    }
    
    public List<Move> getMoves() {
        return _moves;
    }

}
