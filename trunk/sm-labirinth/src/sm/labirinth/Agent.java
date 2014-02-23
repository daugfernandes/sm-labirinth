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
public class Agent {
    Labirinth _labirinth;
    Stack _path;
    Cell _home;
    
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
