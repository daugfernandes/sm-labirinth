/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sm.labirinth;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author david
 */
public class MoveNGTest {
    
    public MoveNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getDirection method, of class Move.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        Move instance = new Move(Direction.North);
        Direction expResult = Direction.North;
        Direction result = instance.getDirection();
        assertEquals(result, expResult);
    }

    /**
     * Test of getWasTried method, of class Move.
     */
    @Test
    public void testGetWasTriedFalse() {
        System.out.println("getWasTried");
        Move instance = new Move(Direction.East);
        boolean expResult = false;
        boolean result = instance.getWasTried();
        assertEquals(result, expResult);
    }

    @Test
    public void testGetWasTriedTrue() {
        System.out.println("getWasTried");
        Move instance = new Move(Direction.East);
        instance.setWasTried(true);
        boolean expResult = true;
        boolean result = instance.getWasTried();
    }

    /**
     * Test of setDirection method, of class Move.
     */
    @Test
    public void testSetDirection() {
        System.out.println("setDirection");
        Direction value = null;
        Move instance = new Move();
        instance.setDirection(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWasTried method, of class Move.
     */
    @Test
    public void testSetWasTried() {
        System.out.println("setWasTried");
        boolean value = false;
        Move instance = new Move();
        instance.setWasTried(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
