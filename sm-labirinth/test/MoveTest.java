/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David Fernandes <davidfernandes@acm.org>
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

  /**
   * Test of getDirection method, of class Move.
   */
  @Test
  public void testGetDirection() {
    System.out.println("getDirection");
    Move instance = new Move();
    Direction expResult = null;
    Direction result = instance.getDirection();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getWasTried method, of class Move.
   */
  @Test
  public void testGetWasTried() {
    System.out.println("getWasTried");
    Move instance = new Move();
    boolean expResult = false;
    boolean result = instance.getWasTried();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
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
