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
public class AgentTest {
    
    public AgentTest() {
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
    public void testConstructor() {
        Agent instance = new Agent(null, null);
        assertNotNull(instance);
    }

    /**
     * Test of ExitFound method, of class Agent.
     */
    @Test
    public void testExitFound() {
        System.out.println("ExitFound");
        Agent instance = null;
        boolean expResult = false;
        boolean result = instance.ExitFound();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Move method, of class Agent.
     */
    @Test
    public void testMove() {
        System.out.println("Move");
        Agent instance = null;
        Cell expResult = null;
        Cell result = instance.Move();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}