/**
 * Class Labirinth
 * Defines the labirinth
 * @author David Fernandes <david.paiva.fernandes@gmail.com>
 * @author João Marques <joaorodr84@gmail.com>
 */

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Labirinth implements java.io.Serializable {

  private static final long serialVersionUID = 7L;

  private Cell[][] _cells;          // Array of cell that form the labirinth
  private Cell _exit;               // Exit cell of the labirinth
  private int labWidth;             // Labirinth width
  private int labHeight;            // Labirinth height

  // Default constructor
  public Labirinth() {
    this(10, 10, new Cell(10, 10, true));
  }

  // Constructor by argument (file with labirinth information)
  public Labirinth(String labirinthFileName) throws IOException {

    String[] labirinthString = readTextFile(labirinthFileName);
    int exitX = -1;

    // find width and height end exit
    int width = 0, height = 0;
    for (String row : labirinthString) {
      height++;
      width = Math.max(width, row.length());
      exitX = row.indexOf("#");
      if (exitX >= 0) {
        _exit = new Cell(height - 1, exitX, true);
      }
    }
    _cells = new Cell[height][width];

    // build cells
    int kRow = 0;
    for (String row : labirinthString) {
      int kCol = 0;
      for (byte ch : row.getBytes()) {
        if (ch == '*') {
          _cells[kRow][kCol] = new Cell(kRow, kCol, true); // '*' isWall
        } else { // tudo o resto é passagem
          _cells[kRow][kCol] = new Cell(kRow, kCol, false);
        }
        kCol++;
      }
      kRow++;
    }

    // build possiblemoves
    for (int idxRow = 0; idxRow < height; idxRow++) {
      for (int idxCol = 0; idxCol < width; idxCol++) {
        if (_cells[idxRow][idxCol].IsPathWay()) {

          Cell cellWest = (idxCol > 0 ? _cells[idxRow][idxCol - 1] : null);
          Cell cellEast = (idxCol < width - 1 ? _cells[idxRow][idxCol + 1] : null);
          Cell cellNorth = (idxRow > 0 ? _cells[idxRow - 1][idxCol] : null);
          Cell cellSouth = (idxRow < height - 1 ? _cells[idxRow + 1][idxCol] : null);

          if (cellWest != null && cellWest.IsPathWay()) {
            _cells[idxRow][idxCol].addPossibleMove(new Move(Direction.West));
          }
          if (cellEast != null && cellEast.IsPathWay()) {
            _cells[idxRow][idxCol].addPossibleMove(new Move(Direction.East));
          }
          if (cellNorth != null && cellNorth.IsPathWay()) {
            _cells[idxRow][idxCol].addPossibleMove(new Move(Direction.North));
          }
          if (cellSouth != null && cellSouth.IsPathWay()) {
            _cells[idxRow][idxCol].addPossibleMove(new Move(Direction.South));
          }
        }
      }
    }
    labWidth = width;
    labHeight = height;
  }

  // Constructor by arguments (width, height, exit)
  public Labirinth(int width, int height, Cell exit) {
    _cells = new Cell[height][width];
    _exit = exit;
  }

  // Width getter
  public int getWidth() {
    return labWidth;
  }

  // Height getter
  public int getHeight() {
    return labHeight;
  }

  /*
   public void setWasTried(int x, int y) {
   this._cells[x][y]
   }
   */
  
  // Reads the given text file and stores the result in a string
  private String[] readTextFile(String fileName) throws FileNotFoundException, IOException {

    StringBuilder sb = new StringBuilder();
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    try {
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append("\n");
        line = br.readLine();
      }
    } finally {
      br.close();
    }
    return sb.toString().split("\n");
  }

  // Exit getter
  public Cell getExit() {
    return _exit;
  }

  // Cell getter (given the coordinates)
  public Cell getCell(int y, int x) {
    return _cells[y][x];
  }

  // Draws the labirinth
  public void Draw(Graphics graphics) {

    if(graphics==null) {
      return;
    }
    
    int height = _cells.length;
    int width = _cells[0].length; // is squared for sure

    graphics.setColor(Color.BLACK);
    graphics.fillRect(0, 0, getWidth(), getHeight());

    for (int idxRow = 0; idxRow < height; idxRow++) {
      for (int idxCol = 0; idxCol < width; idxCol++) {
        Cell thisCell = _cells[idxRow][idxCol];
        graphics.setColor(Color.BLUE);
        if (thisCell.IsWall()) {
          graphics.fillRect(idxCol * 20, idxRow * 20, 20, 20);
        } else {
          graphics.drawRect(idxCol * 20, idxRow * 20, 20, 20);
          graphics.setColor(Color.RED);
          if (thisCell.getPossibleMoves().containsMove(Direction.North)) {
            graphics.drawLine(idxCol * 20 + 10, idxRow * 20 + 10, idxCol * 20 + 10, idxRow * 20 + 5);
            graphics.drawLine(idxCol * 20 + 10, idxRow * 20 + 5, idxCol * 20 + 11, idxRow * 20 + 6);
            graphics.drawLine(idxCol * 20 + 10, idxRow * 20 + 5, idxCol * 20 + 9, idxRow * 20 + 6);
          }
          if (thisCell.getPossibleMoves().containsMove(Direction.South)) {
            graphics.drawLine(idxCol * 20 + 10, idxRow * 20 + 10, idxCol * 20 + 10, idxRow * 20 + 15);
            graphics.drawLine(idxCol * 20 + 10, idxRow * 20 + 15, idxCol * 20 + 11, idxRow * 20 + 14);
            graphics.drawLine(idxCol * 20 + 10, idxRow * 20 + 15, idxCol * 20 + 9, idxRow * 20 + 14);
          }
          if (thisCell.getPossibleMoves().containsMove(Direction.East)) {
            graphics.drawLine(idxCol * 20 + 10, idxRow * 20 + 10, idxCol * 20 + 15, idxRow * 20 + 10);
            graphics.drawLine(idxCol * 20 + 15, idxRow * 20 + 10, idxCol * 20 + 14, idxRow * 20 + 11);
            graphics.drawLine(idxCol * 20 + 15, idxRow * 20 + 10, idxCol * 20 + 14, idxRow * 20 + 9);
          }
          if (thisCell.getPossibleMoves().containsMove(Direction.West)) {
            graphics.drawLine(idxCol * 20 + 10, idxRow * 20 + 10, idxCol * 20 + 5, idxRow * 20 + 10);
            graphics.drawLine(idxCol * 20 + 5, idxRow * 20 + 10, idxCol * 20 + 6, idxRow * 20 + 11);
            graphics.drawLine(idxCol * 20 + 5, idxRow * 20 + 10, idxCol * 20 + 6, idxRow * 20 + 9);
          }
        }
      }
    }

    // exit
    graphics.setColor(Color.YELLOW);
    graphics.drawOval(_exit.getX() * 20 + 10 - 2, _exit.getY() * 20 + 10 - 2, 4, 4);
  }
}