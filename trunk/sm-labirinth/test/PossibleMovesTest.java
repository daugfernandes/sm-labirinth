/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
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
public class PossibleMovesTest {

  public PossibleMovesTest() {
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
   * Test of addMove method, of class PossibleMoves.
   */
  @Test
  public void testAddMove() {
    System.out.println("addMove");
    Move move = null;
    PossibleMoves instance = new PossibleMoves();
    boolean expResult = false;
    boolean result = instance.addMove(move);
    assertEquals(expResult, result);
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
    assertEquals(expResult, result);
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
    List<Move> expResult = null;
    List<Move> result = instance.getMoves();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of containsMove method, of class PossibleMoves.
   */
  @Test
  public void testContainsMove() {
    System.out.println("containsMove");
    Direction dir = null;
    PossibleMoves instance = new PossibleMoves();
    boolean expResult = false;
    boolean result = instance.containsMove(dir);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
