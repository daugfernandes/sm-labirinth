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
public class Labirinth {

    Cell[][] _cells;
    Cell _exit;

    public Labirinth() {
        // by default
        this(10, 10, new Cell(10, 10));
    }

    public Labirinth(int width, int height, Cell exit) {
        _cells = new Cell[width][height];
        _exit = exit;
    }
}
