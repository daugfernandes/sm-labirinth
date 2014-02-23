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
public class DirectionTest {

    public DirectionTest() {
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
    public void testValues() {
        System.out.println("values");
        Direction[] expResult = {Direction.Unknown, Direction.North, Direction.South, Direction.East, Direction.West};
        Direction[] result = Direction.values();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testValueOfSouth() {
        System.out.println("valueOfSouth");
        String name = "South";
        Direction expResult = Direction.South;
        Direction result = Direction.valueOf(name);
        assertEquals(expResult, result);
    }

    @Test
    public void testValueOfNorth() {
        System.out.println("valueOfNorth");
        String name = "North";
        Direction expResult = Direction.North;
        Direction result = Direction.valueOf(name);
        assertEquals(expResult, result);
    }

    @Test
    public void testValueOfEast() {
        System.out.println("valueOfEast");
        String name = "East";
        Direction expResult = Direction.East;
        Direction result = Direction.valueOf(name);
        assertEquals(expResult, result);
    }

    @Test
    public void testValueOfWest() {
        System.out.println("valueOfWest");
        String name = "West";
        Direction expResult = Direction.West;
        Direction result = Direction.valueOf(name);
        assertEquals(expResult, result);
    }

    @Test
    public void testValueOfUnknown() {
        System.out.println("valueOfUnknown");
        String name = "Unknown";
        Direction expResult = Direction.Unknown;
        Direction result = Direction.valueOf(name);
        assertEquals(expResult, result);
    }

}
