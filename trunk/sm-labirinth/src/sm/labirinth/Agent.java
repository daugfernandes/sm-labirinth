/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sm.labirinth;

import java.util.Stack;
import jade.core.behaviours.*;

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
        System.out.println("Hello! Buyer-agent " + getAID().getName() + " is ready.");
        
        // searchFoExit function is defined as a behaviour
        
        // add the searchForExit behaviour to the agent
        addBehaviour(new SearchExit());
    }

    public Agent(Labirinth labirinth, Cell home) {
        this._labirinth = labirinth;
        this._home = home;
        _path = new Stack();
        _path.push(home);
    }
    
    public boolean ExitFound() {
        return _path.peek().equals(_labirinth._exit);
    }
    
    public Cell Move() {
        // try to move;
        return null;
    }
    
    // Searches for the exit if the labyrinth
    private class SearchExit extends SimpleBehaviour {

        @Override
        public void action() {
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
                    
                    // Store the cell for backtracking
                    backTrack.push(currentPosition);
                    
                    // Make a move
                    if (nextMove._direction == Direction.North) currentPosition._y++;
                    else if (nextMove._direction == Direction.South) currentPosition._y--;
                    else if (nextMove._direction == Direction.West) currentPosition._x--;
                    else if (nextMove._direction == Direction.East) currentPosition._x++;
                }
                // Do some backtracking
                else {
                    // While the next move is a tried cell ...
                    while (backTrack.peek().getNextPossibleMove()._wasTried) {
                        
                        // Pop the cell from backtracking
                        Cell nextCell = backTrack.pop();
                        Move backMove = nextCell.getNextPossibleMove();
                        
                        // Make the backtrack move (repeated code... fix this by creating a "move" method)
                        if (backMove._direction == Direction.North) currentPosition._y++;
                        else if (backMove._direction == Direction.South) currentPosition._y--;
                        else if (backMove._direction == Direction.West) currentPosition._x--;
                        else if (backMove._direction == Direction.East) currentPosition._x++;
                    }
                }
            }
        }

        @Override
        public boolean done() {
            //To change body of generated methods, choose Tools | Templates.
            throw new UnsupportedOperationException("Not supported yet.");
        }
        // ...
    }

}
