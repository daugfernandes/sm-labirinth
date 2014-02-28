/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import java.io.IOException;
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
  private AID[] _players;

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
    System.out.println(String.format("LOG %s: Referee is starting.", getAID().getName()));
    Object[] args = getArguments();
    System.out.println(String.format("LOG %s: Args.size=%d [0]==%s", getAID().getName(), args.length, args[0]));

    if (args.length > 0) {
      try {
        _labirinth = new Labirinth((String) args[0]);
        System.out.println(String.format("LOG %s: Labirinth loaded; exit is " + _labirinth.getExit().toString(), getAID().getName()));

        // registering the referee service
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("labirinth_referee");
        sd.setName("olegario-benquerenca");
        dfd.addServices(sd);
        try {
          DFService.register(this, dfd);
          System.out.println(String.format("LOG %s: Referee registered.", getAID().getName()));

        } catch (FIPAException fe) {
          System.out.println(String.format("ERR %s: %s.", getAID().getName(), fe.getMessage()));
        }

      } catch (IOException ex) {
        Logger.getLogger(Referee.class.getName()).log(Level.SEVERE, null, ex);
      }
    } else {
      System.out.println(String.format("WRN %s: No Labirinth specified.", getAID().getName()));
      doDelete();
    }
  }

  @Override
  protected void takeDown() {
    // deregister
    try {
      DFService.deregister(this);
    } catch (FIPAException fe) {
      System.out.println(String.format("ERR %s: %s.", getAID().getName(), fe.getMessage()));
    }
    System.out.println(String.format("LOG %s: Referee is terminating!", getAID().getName()));
  }

}
