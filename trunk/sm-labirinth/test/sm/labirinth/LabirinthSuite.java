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
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author david
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({sm.labirinth.CellTest.class, sm.labirinth.DirectionTest.class, sm.labirinth.PossibleMovesTest.class, sm.labirinth.LabirinthTest.class, sm.labirinth.AgentTest.class, sm.labirinth.MoveTest.class, sm.labirinth.SmLabirinthTest.class})
public class LabirinthSuite {

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
