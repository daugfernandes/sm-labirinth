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

    @Test
    public void testParameterLessConstructor() {
        Labirinth instance = new Labirinth();
        assertNotNull(instance);
    }

    @Test
    public void testConstructor() {
        Labirinth instance = new Labirinth(10, 10, new Cell(10, 10));
        assertNotNull(instance);
    }

}
