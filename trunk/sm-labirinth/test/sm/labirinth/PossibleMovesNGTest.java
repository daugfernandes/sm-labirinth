/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sm.labirinth;

import java.util.List;
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
public class PossibleMovesNGTest {
    
    public PossibleMovesNGTest() {
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
     * Test of addMove method, of class PossibleMoves.
     */
    @Test
    public void testAddMove() {
        System.out.println("addMove");
        Move move = null;
        PossibleMoves instance = new PossibleMoves();
        boolean expResult = false;
        boolean result = instance.addMove(move);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextPossibleMove method, of class PossibleMoves.
     */
    @Test
    public void testGetNextPossibleMove() {
        System.out.println("getNextPossibleMove");
        PossibleMoves instance = new PossibleMoves();
        Move expResult = null;
        Move result = instance.getNextPossibleMove();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMoves method, of class PossibleMoves.
     */
    @Test
    public void testGetMoves() {
        System.out.println("getMoves");
        PossibleMoves instance = new PossibleMoves();
        List expResult = null;
        List result = instance.getMoves();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
