/**
 * Class Referee (agent)
 * @author David Fernandes <david.paiva.fernandes@gmail.com>
 * @author João Marques <joaorodr84@gmail.com>
 */

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Referee extends Agent {

  private static final long serialVersionUID = 15L;

  private Labirinth _labirinth;
  private HashMap<AID, Boolean> _players;
  private HashMap<AID, Point> _playersPosition;
  private boolean boardFound = false;
  private AID boardAgent;

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

    _players = new HashMap<>();
    _playersPosition = new HashMap<>();
    
    // Initialize
    if (args.length > 0) {
      try {
        // Generating labirinth
        _labirinth = new Labirinth((String) args[0]);
        System.out.println(String.format("LOG %s: Labirinth loaded; exit is " + _labirinth.getExit().toString(), getAID().getName()));

        // Registering the referee service in the yellow-pages
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

        // Sends the labirinth to the board agent
        addBehaviour(new CyclicBehaviour() {
          private static final long serialVersionUID = 1L;

          @Override
          public void action() {
            try {
              if (!boardFound) {
                DFAgentDescription template = new DFAgentDescription();
                ServiceDescription sd = new ServiceDescription();
                sd.setType("labirinth_board");
                template.addServices(sd);
                DFAgentDescription[] result = DFService.search(myAgent, template);
                if (result.length == 1) {
                  boardAgent = result[0].getName();
                  boardFound = true;
                  System.out.println(String.format("LOG %s: board found AID: %s", getAID().getName(), boardAgent.getName()));
                  ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                  msg.addReceiver(boardAgent);
                  msg.setConversationId("lab");
                  msg.setLanguage("jr");
                  msg.setOntology("labirinth-ontology");
                  msg.setContentObject(_labirinth);
                  send(msg);
                }
              }
            } catch (FIPAException ex) {
              System.out.println(String.format("ERR %s: %s", getAID().getName(), ex.getMessage()));
            } catch (IOException ex) {
              System.out.println(String.format("ERR %s: %s", getAID().getName(), ex.getMessage()));
            }
          }
        });

        // Listen to players and reacts accordingly
        addBehaviour(new CyclicBehaviour() {
          private static final long serialVersionUID = 1L;

          @Override
          public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
              try {
                  
                // Being found by the players, the referee sends them the labirinth
                if (msg.getConversationId().equals("alive")) {
                  String title = msg.getContent();
                  System.out.println(String.format("LOG %s: Message received from %s is {%s}", getAID().getName(), msg.getSender().getName(), title));
                  if (!_players.containsKey(msg.getSender())) {
                    _players.put(msg.getSender(), false);
                    ACLMessage reply = new ACLMessage(ACLMessage.INFORM);
                    reply.addReceiver(msg.getSender());
                    reply.setContentObject(_labirinth);
                    myAgent.send(reply);
                  }
                }  
                /* When a player finds the exit, the referee informs the other
                     players that they lost, terminates the Board Agent and shuts down */
                else if (msg.getConversationId().equals("found")) {
                  _players.put(msg.getSender(), true);
                  // game over!
                  for (AID player : _players.keySet()) {
                    if (!player.getName().equals(msg.getSender().getName())) {
                      ACLMessage reply = new ACLMessage(ACLMessage.INFORM);
                      reply.addReceiver(player);
                      reply.setConversationId("looser");
                      myAgent.send(reply);
                    }
                    TerminateBoard();
                    doDelete();
                  }
                }
                
                // If no player finds the exit, the referee shuts everything down
                else if (msg.getConversationId().equals("giveup")) {
                  _players.put(msg.getSender(), true);
                  if (AllAgentsDied()) {
                    TerminateBoard();
                    doDelete();
                  }
                } else if (msg.getConversationId().equals("draw")) {
                  BuildPlayersPosition(msg);
                }
              } catch (IOException ex) {
                Logger.getLogger(Referee.class.getName()).log(Level.SEVERE, null, ex);
              }
            }
          }
        });

      } catch (IOException ex) {
        Logger.getLogger(Referee.class.getName()).log(Level.SEVERE, null, ex);
      }
    } else {
      System.out.println(String.format("WRN %s: No Labirinth specified.", getAID().getName()));
      doDelete();
    }
  } // End of setup

  private void BuildPlayersPosition(ACLMessage msg) {

    if (!msg.getContent().isEmpty()) {
      Integer y = Integer.parseInt(msg.getContent().split(";")[0]);
      Integer x = Integer.parseInt(msg.getContent().split(";")[1]);

      _playersPosition.put(msg.getSender(), new Point(x, y));

      String result = "";
      for (Point p : _playersPosition.values()) {
        result += result.isEmpty() ? "" : "/";
        result += String.format(";%d;%d", p.x, p.y);
      }

      if (boardFound) {
        ACLMessage newMsg = new ACLMessage(ACLMessage.INFORM);
        newMsg.addReceiver(boardAgent);
        newMsg.setConversationId("draw");
        newMsg.setContent(result);
        send(newMsg);
      }
    }

  }

  // Ckecks if all the agents are terminated
  private boolean AllAgentsDied() {
    for (Map.Entry<AID, Boolean> item : _players.entrySet()) {
      if (!item.getValue()) {
        return false;
      }
    }
    return true;
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

  // Terminates the board agent
  private void TerminateBoard() {
    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
    msg.addReceiver(boardAgent);
    msg.setLanguage("jr");
    msg.setOntology("labirinth-ontology");
    msg.setConversationId("end");
    send(msg);
  }
} // End of referee
