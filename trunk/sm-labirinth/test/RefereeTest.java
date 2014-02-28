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
public class RefereeTest {

  public RefereeTest() {
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
   * Test of setup method, of class Referee.
   */
  @Test
  public void testSetup() {
    System.out.println("setup");
    Referee instance = new Referee();
    instance.setup();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of takeDown method, of class Referee.
   */
  @Test
  public void testTakeDown() {
    System.out.println("takeDown");
    Referee instance = new Referee();
    instance.takeDown();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
