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
public class Move {
    Direction _direction;
    boolean _wasTried;
    
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
    
}
