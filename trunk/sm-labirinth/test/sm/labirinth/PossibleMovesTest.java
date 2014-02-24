/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sm.labirinth;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author david
 */
public class PossibleMovesTest {
    
    public PossibleMovesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddValidMove() {
        System.out.println("addValidMove");
        PossibleMoves instance = new PossibleMoves();
        instance.addMove(new Move(Direction.North));
        instance.addMove(new Move(Direction.South));
        instance.addMove(new Move(Direction.East));
        assertTrue(instance.addMove(new Move(Direction.West)));
    }

    @Test
    public void testAddInvalidMove() {
        System.out.println("addInvalidMove");
        PossibleMoves instance = new PossibleMoves();
        instance.addMove(new Move(Direction.North));
        instance.addMove(new Move(Direction.South));
        instance.addMove(new Move(Direction.East));
        assertFalse(instance.addMove(new Move(Direction.East)));
    }

    @Test
    public void testGetNextPossibleMove() {
        System.out.println("getNextPossibleMove");
        PossibleMoves instance = new PossibleMoves();
        instance.addMove(new Move(Direction.North));
        instance.addMove(new Move(Direction.South));
        instance.addMove(new Move(Direction.East));
        assertNotNull(instance.getNextPossibleMove());
    }

    @Test
    public void testGetNoNextPossibleMove() {
        System.out.println("getNoNextPossibleMove");
        PossibleMoves instance = new PossibleMoves();
        
        Move m = new Move(Direction.North);
        m.setWasTried(true);
        instance.addMove(m);
        
        m = new Move(Direction.South);
        m.setWasTried(true);
        instance.addMove(m);
        
        m = new Move(Direction.East);
        m.setWasTried(true);
        instance.addMove(m);
        
        m = new Move(Direction.West);
        m.setWasTried(true);
        instance.addMove(m);

        assertNull(instance.getNextPossibleMove());
    }
    
}
