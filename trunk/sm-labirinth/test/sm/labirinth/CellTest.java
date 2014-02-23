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
public class CellTest {

    public CellTest() {
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
    public void testConstructorParameterLess() {
        System.out.println("Parameterless Constructor");
        Cell instance = new Cell();
        assertNotNull(instance);
    }

    @Test
    public void testConstructor() {
        System.out.println("Constructor");
        Cell instance = new Cell(10, 10);
        assertNotNull(instance);
    }

    @Test
    public void testInUseTrue() {
        System.out.println("InUseTrue");
        Cell instance = new Cell(10, 10);
        assertTrue(instance.inUse());
    }

    @Test
    public void testInUseFalse() {
        System.out.println("InUseFalse");
        Cell instance = new Cell();
        assertFalse(instance.inUse());
    }

    @Test
    public void testGetNextPossibleMoveWithNoMovesPerformedYet() {
        System.out.println("testGetNextPossibleMoveWithNoMovesPerformedYet");
        Cell instance = new Cell(10, 10);
        instance.addPossibleMove(new Move(Direction.North));
        instance.addPossibleMove(new Move(Direction.South));
        instance.addPossibleMove(new Move(Direction.West));
        instance.addPossibleMove(new Move(Direction.East));
        assertNotNull(instance.getNextPossibleMove());
    }

    @Test
    public void testGetNextPossibleMoveWithAllMovesPerformed() {
        System.out.println("testGetNextPossibleMoveWithAllMovesPerformed");
        Cell instance = new Cell(10, 10);

        Move m = new Move(Direction.North);
        m.setWasTried(true);
        instance.addPossibleMove(m);

        m = new Move(Direction.South);
        m.setWasTried(true);
        instance.addPossibleMove(m);

        m = new Move(Direction.East);
        m.setWasTried(true);
        instance.addPossibleMove(m);

        m = new Move(Direction.West);
        m.setWasTried(true);
        instance.addPossibleMove(m);

        assertNull(instance.getNextPossibleMove());
    }

    @Test
    public void testAddPossibleMove() {
        System.out.println("addPossibleMove");
        Cell instance = new Cell(10, 10);
        instance.addPossibleMove(new Move(Direction.North));
        instance.addPossibleMove(new Move(Direction.South));
        instance.addPossibleMove(new Move(Direction.West));
        instance.addPossibleMove(new Move(Direction.East));
        assertEquals(instance.getPossibleMoves().getMoves().size(), 4);
    }

}
