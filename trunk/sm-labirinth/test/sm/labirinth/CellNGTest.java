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
public class CellNGTest {
    
    public CellNGTest() {
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
     * Test of inUse method, of class Cell.
     */
    @Test
    public void testInUse() {
        System.out.println("inUse");
        Cell instance = new Cell();
        boolean expResult = false;
        boolean result = instance.inUse();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPossibleMove method, of class Cell.
     */
    @Test
    public void testAddPossibleMove() {
        System.out.println("addPossibleMove");
        Move move = null;
        Cell instance = new Cell();
        boolean expResult = false;
        boolean result = instance.addPossibleMove(move);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextPossibleMove method, of class Cell.
     */
    @Test
    public void testGetNextPossibleMove() {
        System.out.println("getNextPossibleMove");
        Cell instance = new Cell();
        Move expResult = null;
        Move result = instance.getNextPossibleMove();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPossibleMoves method, of class Cell.
     */
    @Test
    public void testGetPossibleMoves() {
        System.out.println("getPossibleMoves");
        Cell instance = new Cell();
        PossibleMoves expResult = null;
        PossibleMoves result = instance.getPossibleMoves();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
