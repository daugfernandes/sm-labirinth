/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.labirinth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author david.paiva.fernandes@gmail.com
 * @author joaorodr84@gmail.com
 */
public class Labirinth {

  private Cell[][] _cells;
  private Cell _exit;

  public Labirinth() {
    // by default
    this(10, 10, new Cell(10, 10));
  }

  public Labirinth(String labirinthFileName) throws IOException {

    String[] labirinthString = readTextFile(labirinthFileName);
    int exitX = -1, exitY = -1;

    // find width and height
    int width = 0, height = 0;
    for (String row : labirinthString) {
      height++;
      width = Math.max(width, row.length());
      exitX = row.indexOf("*");
      if (exitX >= 0) {
        exitY = height - 1;
      }
    }
    _cells = new Cell[width][height];
    _exit = new Cell(exitX, exitY);
  }

  public Labirinth(int width, int height, Cell exit) {
    _cells = new Cell[width][height];
    _exit = exit;
  }

  private String[] readTextFile(String fileName) throws FileNotFoundException, IOException {

    StringBuilder sb = new StringBuilder();
    BufferedReader br = new BufferedReader(new FileReader("file.txt"));
    try {
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
    } finally {
      br.close();
    }
    return sb.toString().split(System.lineSeparator());
  }
  
  public Cell getExit() {
    return _exit;
  }
}
