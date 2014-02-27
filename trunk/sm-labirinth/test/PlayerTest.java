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
public class PlayerTest {
  
  public PlayerTest() {
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
   * Test of setup method, of class Player.
   */
  @Test
  public void testSetup() {
    System.out.println("setup");
    Player instance = null;
    instance.setup();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of ExitFound method, of class Player.
   */
  @Test
  public void testExitFound() {
    System.out.println("ExitFound");
    Player instance = null;
    boolean expResult = false;
    boolean result = instance.ExitFound();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of Move method, of class Player.
   */
  @Test
  public void testMove() {
    System.out.println("Move");
    Player instance = null;
    Cell expResult = null;
    Cell result = instance.Move();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
