/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sm.labirinth;

import java.util.Stack;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SequentialBehaviour;

/**
 *
 * @author david
 */
public class Agent extends jade.core.Agent {
    Labirinth _labirinth;
    Stack _path;
    Cell _home;

    @Override
    protected void setup() {
        // Printout a welcome message
        System.out.println("Hello! Agent " + getAID().getName() + " is ready.");
        
        // Creates new parallel behaviour for concurrency
        ParallelBehaviour pb = new ParallelBehaviour(ParallelBehaviour.WHEN_ANY);
        
        // Creates new sequential behaviour for Search and Offer
        SequentialBehaviour sb = new SequentialBehaviour();
        
        // Adds the SearchExit and OfferExit behaviours to the sequential behaviour
        sb.addSubBehaviour(new SearchExit());
        sb.addSubBehaviour(new OfferExit());
        
        // Adds the SB and ListenToOffers behaviours to the PB
        pb.addSubBehaviour(sb);
        pb.addSubBehaviour(new ListenToOffers());
        
        // Adds the PB to the agent
        this.addBehaviour(pb);
    }

    public Agent(Labirinth labirinth, Cell home) {
        this._labirinth = labirinth;
        this._home = home;
        _path = new Stack();
        _path.push(home);
    }
    
    public boolean ExitFound() {
        return _path.peek().equals(_labirinth.getExit());
    }
    
    public Cell Move() {
        // try to move;
        return null;
    }
    
    // Searches for the exit if the labyrinth
    private class SearchExit extends SimpleBehaviour {

        @Override
        public void action() {
          
          // JOÃO ------------------
          // _path seria a stack para suportar isto!!!!!
            Stack<Cell> backTrack = new Stack();
            Cell currentPosition = _home;
            
            // Run until the exit is found
            while (!ExitFound()) {
                Move nextMove = currentPosition.getNextPossibleMove();
                
                // Consider the case where only one move is possible ... fix this
                // ... one possible move code ...
                
                // If there is a possible move ...
                if (nextMove != null) {
                    // Set the move as tried
                    currentPosition.getNextPossibleMove().setWasTried(true);
                    
                    /* Store the cell for backtracking only if
                        there is more than one possible move */
                    if (currentPosition.getNextPossibleMove() != null)
                        backTrack.push(currentPosition);
                    
                    // Make a move
                    currentPosition.makeNextMove();
                }
                // Do some backtracking
                else {
                    // While the next move is a tried cell ...
                    while (backTrack.peek().getNextPossibleMove()._wasTried) {
                        
                        // Pop the cell from backtracking
                        Cell backCell = backTrack.pop();
                        
                        // Make the backtrack move
                        currentPosition.moveTo(backCell);
                    }
                }
            }
        }

        @Override
        public boolean done() {
            return ExitFound();
        }
    }
    
    // Searches for the exit if the labyrinth
    private class OfferExit extends SimpleBehaviour {
        // ...
        
        @Override
        public void action() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean done() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    // Listens to offers from other agents / the referee
    private class ListenToOffers extends SimpleBehaviour {

        @Override
        public void action() {
            /*
            if (!proposals.size()) return
            else {
                while (!proposals.size()) {
                    // try to negotiate
                }
            }
            */
        }

        @Override
        public boolean done() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }

}
