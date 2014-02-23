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
public class MoveTest {
    
    public MoveTest() {
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
    public void testConstructorParameterless() {
        System.out.println("Parameterless Constructor");
        Move instance = new Move();
        assertNotNull(instance);
    }

    @Test
    public void testConstructor() {
        System.out.println("Constructor");
        Move instance = new Move(Direction.North);
        assertNotNull(instance);
    }

    /**
     * Test of getDirection method, of class Move.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        Move instance = new Move(Direction.East);
        Direction expResult = Direction.East;
        Direction result = instance.getDirection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWasTried method, of class Move.
     */
    @Test
    public void testGetWasTried() {
        System.out.println("getWasTried");
        Move instance = new Move();
        boolean result = instance.getWasTried();
        assertFalse(result);
    }

    /**
     * Test of setDirection method, of class Move.
     */
    @Test
    public void testSetDirection() {
        System.out.println("setDirection");
        Direction value = Direction.South;
        Move instance = new Move();
        instance.setDirection(value);
        assertEquals(value, instance.getDirection());
    }

    /**
     * Test of setWasTried method, of class Move.
     */
    @Test
    public void testSetWasTried() {
        System.out.println("setWasTried");
        Move instance = new Move();
        instance.setWasTried(true);
        assertTrue(instance.getWasTried());
    }
    
}
