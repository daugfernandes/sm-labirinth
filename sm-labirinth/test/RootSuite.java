/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author David Fernandes <davidfernandes@acm.org>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({MoveTest.class, CellTest.class, PlayerTest.class, DirectionTest.class, PossibleMovesTest.class, LabirinthTest.class, RefereeTest.class, sm.SmSuite.class})
public class RootSuite {

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

}
