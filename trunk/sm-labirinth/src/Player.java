/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import java.util.Stack;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class Player extends Agent {

  private static final long serialVersionUID = 9L;

  private Labirinth _labirinth;
  private Stack<Cell> _path;        // J: acrescentei <Cell> para o programa saber que é uma stack de células
  private Cell _home;
  private AID refereeAgent;
  private boolean refereeFound;
  private boolean labirinthReceived;

  public Cell generateHome() {
    if (labirinthReceived) {
      Random randomGenerator = new Random();

      Cell initPos;
      do {
        int homeX = randomGenerator.nextInt(_labirinth.getWidth()),
                homeY = randomGenerator.nextInt(_labirinth.getHeight());

        initPos = _labirinth.getCell(homeY, homeX);
      } while (initPos.equals(_labirinth.getExit()) || initPos.IsWall());

      return initPos;
    } else {
      return null;
    }
  }

  @Override
  protected void setup() {
    // Printout a welcome message
    System.out.println(String.format("LOG %S: Player is starting!", getAID().getName()));

    //this._labirinth = labirinth;
    //this._home = home;
    _path = new Stack<>();
    refereeFound = false;

    // Main sequential behaviour for Initialize and PB
    SequentialBehaviour main = new SequentialBehaviour();

    // Adds the bhaviour that searches for a referee and loads the labirinth
    main.addSubBehaviour(new Initialize());

    // Creates new parallel behaviour for concurrency
    ParallelBehaviour pb = new ParallelBehaviour(ParallelBehaviour.WHEN_ANY);

    // Creates new sequential behaviour for Search and Offer
    SequentialBehaviour sb = new SequentialBehaviour();

    // Adds the SearchExit and OfferExit behaviours to the sequential behaviour
    sb.addSubBehaviour(new SearchExit());
    sb.addSubBehaviour(new Conclusion());

    // Adds the SB and ListenToOffers behaviours to the PB
    pb.addSubBehaviour(sb);
    pb.addSubBehaviour(new ListenToMessages());

    // Adds the PB behaviour to the agent
    main.addSubBehaviour(pb);

    // Adds the main behaviour to the agent
    addBehaviour(main);
  }

  @Override
  protected void takeDown() {
    System.out.println(String.format("LOG %s: Player is terminating!", getAID().getName()));
  }

  public boolean ExitFound() {
    return _path.size() != 0 && (_path.peek().getX() == _labirinth.getExit().getX() && _path.peek().getY() == _labirinth.getExit().getY());
  }

  public Cell Move() {
    // try to move;
    return null;
  }

  // Waits for the referee to transmit the labyrinth object and processes it
  private class Initialize extends SimpleBehaviour {

    private static final long serialVersionUID = 10L;
    boolean finished = false;

    @Override
    public void action() {
      while (!finished) {
        try {
          if (!refereeFound) {
            DFAgentDescription template = new DFAgentDescription();
            ServiceDescription sd = new ServiceDescription();
            sd.setType("labirinth_referee");
            template.addServices(sd);
            DFAgentDescription[] result = DFService.search(myAgent, template);
            if (result.length == 1) {
              refereeAgent = result[0].getName();
              refereeFound = true;
              System.out.println(String.format("LOG %s: referee found AID: %s", getAID().getName(), refereeAgent.getName()));
              ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
              msg.addReceiver(refereeAgent);
              msg.setLanguage("jr");
              msg.setOntology("labirinth-ontology");
              msg.setConversationId("alive");
              send(msg);
            } else {
              System.out.println(String.format("WRN %s: zeros or more than one referee. #%d", getAID().getName(), result.length));
            }
          } else {
            ACLMessage msg = receive();
            if (msg != null) {
              try {
                Object obj = msg.getContentObject();
                if (obj instanceof Labirinth) {
                  _labirinth = (Labirinth) obj;
                  System.out.println(String.format("LOG %s: Labirinth received Exit = %s", getAID().getName(), _labirinth.getExit().toString()));
                  labirinthReceived = true;
                  _home = generateHome();
                  System.out.println(String.format("LOG %s: Home = %s", getAID().getName(), _home.toString()));
                }
              } catch (UnreadableException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
              }
            }
          }
          finished = refereeFound && labirinthReceived;
        } catch (FIPAException ex) {
          System.out.println(String.format("ERR %s: %s", getAID().getName(), ex.getMessage()));
        }
      }
    }

    @Override
    public boolean done() {
      return finished;
    }
  }

  // Searches for the exit
  private class SearchExit extends SimpleBehaviour {

    private static final long serialVersionUID = 2L;

    @Override
    public void action() {

      if (labirinthReceived) {
        Cell currentPosition = _home;
        _path.push(currentPosition);

        // Run until the exit is found
        do {

          long i = System.currentTimeMillis();
          while (System.currentTimeMillis() - i < 200) {
          }

          // inform referes of agent's position
          ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
          msg.addReceiver(refereeAgent);
          msg.setLanguage("jr");
          msg.setOntology("labirinth-ontology");
          msg.setConversationId("draw");
          msg.setContent(String.format("%d;%d", currentPosition.getY(), currentPosition.getX()));
          send(msg);

          Move nextMove = currentPosition.getNextPossibleMove();

          // If there is a possible move ...
          if (nextMove != null) {

            // Set the move as tried
            nextMove.setWasTried(true);

            // Make a move
            currentPosition = _labirinth.getCell(currentPosition.getY() + nextMove.getIncY(), currentPosition.getX() + nextMove.getIncX());

            if (!currentPosition.getWasTried()) {
              // Keep the current position in the path
              _path.push(currentPosition);
            }

          } else {
            // Make the backtrack move
            currentPosition = _path.pop();
            //System.out.println(String.format("LOG %s: Moved backward to = %s", getAID().getName(), currentPosition.toString()));
            if (_path.size() == 0) {
              break;
            }
          }
        } while (!ExitFound());

      }
    }

    @Override
    public boolean done() {
      return true || ExitFound();
    }

  }

  // Informs that the exit of the labyrinth was found
  private class Conclusion extends SimpleBehaviour {

    private static final long serialVersionUID = 3L;

    @Override
    public void action() {

      ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
      msg.addReceiver(refereeAgent);
      msg.setLanguage("jr");
      msg.setOntology("labirinth-ontology");

      if (ExitFound()) {
        System.out.println(String.format("LOG %s: Exit found!", getAID().getName()));
        msg.setConversationId("found");
        send(msg);
      } else {
        System.out.println(String.format("LOG %s: Player can't find exit!", getAID().getName()));
        msg.setConversationId("giveup");
        send(msg);
      }

      doDelete();
    }

    @Override
    public boolean done() {
      return true;
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  }

  // Listens to offers from other agents / the referee (yup)
  private class ListenToMessages extends CyclicBehaviour {

    private static final long serialVersionUID = 4L;

    // e como é que um player comunica com árbitro?
    // não não
    // fds só para isto
    // isto vai ser fácil
    // assim que se descobrir com é que os gajos falam .....
    // outra coisa
    // as mensagens que se enviam podem ser serializações de classes complicadas?!?
    // o arbitro vai ter que enviar o labirinto aos agentes
    // como?
    // exacto: um objecto serializado
    // suponho que sim
    @Override
    public void action() {

      ACLMessage msg = receive();
      if (msg != null) {
        if (msg.getConversationId().equals("looser")) {
          System.out.println(String.format("LOG %s: Player is a looser.", getAID().getName()));
          doDelete();
        }
      }

    }
  }
}
