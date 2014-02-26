/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.labirinth;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author david
 */
public class LabirinthNGTest {

  public LabirinthNGTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @BeforeMethod
  public void setUpMethod() throws Exception {
  }

  @AfterMethod
  public void tearDownMethod() throws Exception {
  }

  @Test
  public void testConstructorWithFile() throws IOException {
    Labirinth lab = new Labirinth("/home/david/temp/lab1");
    assertNotNull(lab);
  }

  @Test
  public void testDrawWithFile() throws IOException {
    Labirinth lab = new Labirinth("/home/david/temp/lab1");
    BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics2D = image.createGraphics();
    lab.Draw(graphics2D);
    ImageIO.write(image, "png", new File("/home/david/temp/lab1.png"));
    graphics2D.dispose();
  }

  @Test
  public void testSomeMethod() {
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
