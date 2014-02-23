/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.labirinth;

import java.util.Stack;

/**
 *
 * @author david
 */
public class Agent extends jade.core.Agent {

    String name;
    Labirinth _labirinth;
    Stack _path;
    Cell _home;

    @Override
    protected void setup() {
// Printout a welcome message
        System.out.println("Hello! Buyer-agent " + getAID().getName() + " is ready.");
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

}
