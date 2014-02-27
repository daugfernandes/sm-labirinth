/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Graphics;
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
public class LabirinthTest {
  
  public LabirinthTest() {
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
   * Test of getExit method, of class Labirinth.
   */
  @Test
  public void testGetExit() {
    System.out.println("getExit");
    Labirinth instance = new Labirinth();
    Cell expResult = null;
    Cell result = instance.getExit();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of Draw method, of class Labirinth.
   */
  @Test
  public void testDraw() {
    System.out.println("Draw");
    Graphics graphics = null;
    Labirinth instance = new Labirinth();
    instance.Draw(graphics);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
