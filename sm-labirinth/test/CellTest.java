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

  /**
   * Test of inUse method, of class Cell.
   */
  @Test
  public void testInUse() {
    System.out.println("inUse");
    Cell instance = new Cell();
    boolean expResult = false;
    boolean result = instance.inUse();
    assertEquals(expResult, result);
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
    assertEquals(expResult, result);
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
    assertEquals(expResult, result);
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
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of SetIsWall method, of class Cell.
   */
  @Test
  public void testSetIsWall() {
    System.out.println("SetIsWall");
    Cell instance = new Cell();
    instance.SetIsWall();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of SetIsPathWay method, of class Cell.
   */
  @Test
  public void testSetIsPathWay() {
    System.out.println("SetIsPathWay");
    Cell instance = new Cell();
    instance.SetIsPathWay();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of IsPathWay method, of class Cell.
   */
  @Test
  public void testIsPathWay() {
    System.out.println("IsPathWay");
    Cell instance = new Cell();
    boolean expResult = false;
    boolean result = instance.IsPathWay();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of IsWall method, of class Cell.
   */
  @Test
  public void testIsWall() {
    System.out.println("IsWall");
    Cell instance = new Cell();
    boolean expResult = false;
    boolean result = instance.IsWall();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getX method, of class Cell.
   */
  @Test
  public void testGetX() {
    System.out.println("getX");
    Cell instance = new Cell();
    int expResult = 0;
    int result = instance.getX();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getY method, of class Cell.
   */
  @Test
  public void testGetY() {
    System.out.println("getY");
    Cell instance = new Cell();
    int expResult = 0;
    int result = instance.getY();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of makeNextMove method, of class Cell.
   */
  @Test
  public void testMakeNextMove() {
    System.out.println("makeNextMove");
    Cell instance = new Cell();
    instance.makeNextMove();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of moveTo method, of class Cell.
   */
  @Test
  public void testMoveTo() {
    System.out.println("moveTo");
    Cell other = null;
    Cell instance = new Cell();
    instance.moveTo(other);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toString method, of class Cell.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    Cell instance = new Cell();
    String expResult = "";
    String result = instance.toString();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
