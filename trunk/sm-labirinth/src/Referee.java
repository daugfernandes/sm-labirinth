/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ReceiverBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david.paiva.fernandes@gmail.com
 * @author joaorodr84@gmail.com
 */
public class Referee extends Agent {

  private static final long serialVersionUID = 1L;

  private Labirinth _labirinth;
  private List<Player> _agents;
  private List<AID> _players;
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

    _players = new ArrayList<>();

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

                } else {

//                  System.out.println(String.format("WRN %s: zeros or more than one board. #%d", getAID().getName(), result.length));
                }
              }
            } catch (FIPAException ex) {
              System.out.println(String.format("ERR %s: %s", getAID().getName(), ex.getMessage()));
            } catch (IOException ex) {
              System.out.println(String.format("ERR %s: %s", getAID().getName(), ex.getMessage()));
            }
          }
        });

        // setup a cyclic behaviour to listen to players
        addBehaviour(new CyclicBehaviour() {
          private static final long serialVersionUID = 1L;

          @Override
          public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
              try {
                String title = msg.getContent();
                System.out.println(String.format("LOG %s: Message received from %s is {%s}", getAID().getName(), msg.getSender().getName(), title));
                if (!_players.contains(msg.getSender())) {
                  _players.add(msg.getSender());
                  ACLMessage reply = new ACLMessage(ACLMessage.INFORM);
                  reply.addReceiver(msg.getSender());
                  reply.setContentObject(_labirinth);
                  myAgent.send(reply);
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
