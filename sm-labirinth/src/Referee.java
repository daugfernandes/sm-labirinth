/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david.paiva.fernandes@gmail.com
 * @author joaorodr84@gmail.com
 */
public class Referee extends jade.core.Agent {

  private static final long serialVersionUID = 1L;

  private Labirinth _labirinth;
  private List<Player> _agents;

  /*public Referee(String labirinthFilename) throws IOException {
   this._labirinth = new Labirinth(labirinthFilename);
   this._agents = new ArrayList<Player>();
   }

   public List<Player> getAgents() {
   return Collections.unmodifiableList(_agents);
   }

   public Labirinth getLabirinth() {
   return _labirinth;
   }*/
  @Override
  protected void setup() {
    System.out.println("LOG: Referee-agent " + getAID().getName() + " is ready.");
    Object[] args = getArguments();
    System.out.println(String.format("LOG: Args.size=%d [0]==%s", args.length, args[0]));

    if (args.length > 0) {
      try {
        _labirinth = new Labirinth((String) args[0]);
        System.out.println("LOG: Labirinth loaded; exit is " + _labirinth.getExit().toString());
      } catch (IOException ex) {
        Logger.getLogger(Referee.class.getName()).log(Level.SEVERE, null, ex);
      }
    } else {
      System.out.println("WARN: No Labirinth specified.");
      doDelete();
    }
  }

  @Override
  protected void takeDown() {
    System.out.println("LOG: Referee-agent " + getAID().getName() + " is terminating!");
  }

}
