/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import java.util.Stack;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

/**
 *
 * @author david
 */
public class Player extends jade.core.Agent {

  Labirinth _labirinth;
  Stack<Cell> _path;        // J: acrescentei <Cell> para o programa saber que é uma stack de células
  Cell _home;
  private AID refereeAgent;
  private boolean refereeFound;

  @Override
  protected void setup() {
    // Printout a welcome message
    System.out.println("Hello! Agent " + getAID().getName() + " is ready.");

    //this._labirinth = labirinth;
    //this._home = home;
    //_path = new Stack<Cell>();
    refereeFound = false;
    // search for a referee
    DFAgentDescription template = new DFAgentDescription();
    ServiceDescription sd = new ServiceDescription();
    sd.setType("labirinth_referee");
    template.addServices(sd);

    try {
      DFAgentDescription[] result = DFService.search(this, template);
      if (result.length == 1) {
        refereeAgent = result[0].getName();
        refereeFound = true;
        System.out.println(String.format("LOG %s: referee found AID: %s", getAID().getName(), refereeAgent.getName()));
      } else {
        System.out.println(String.format("WRN %s: zeros or more than one referee. #%d", getAID().getName(), result.length));
      }
    } catch (FIPAException fe) {
      System.out.println(String.format("ERR %s: %s", getAID().getName(), fe.getMessage()));
    }

    // Creates an initial sequential behaviour for GetLab and PB
    SequentialBehaviour isb = new SequentialBehaviour();

    // Creates new parallel behaviour for concurrency
    ParallelBehaviour pb = new ParallelBehaviour(ParallelBehaviour.WHEN_ANY);

    // Creates new sequential behaviour for Search and Offer
    SequentialBehaviour sb = new SequentialBehaviour();

    // Adds the SearchExit and OfferExit behaviours to the sequential behaviour
    sb.addSubBehaviour(new SearchExit());
    sb.addSubBehaviour(new OfferExit());

    // Adds the SB and ListenToOffers behaviours to the PB
    pb.addSubBehaviour(sb);
    pb.addSubBehaviour(new ListenToOffers());

    // Adds the GetLab and PB behaviour to ISB
    isb.addSubBehaviour(new GetLab());
    isb.addSubBehaviour(pb);

    // Adds the PB to the agent
    this.addBehaviour(pb);
  }

  @Override
  protected void takeDown() {
    System.out.println(String.format("LOG %s: Player-agent is terminating!", getAID().getName()));
  }

  public boolean ExitFound() {
    return _path.peek().equals(_labirinth.getExit());
  }

  public Cell Move() {
    // try to move;
    return null;
  }

  // Waits for the referee to transmit the labyrinth object and processes it
  private class GetLab extends CyclicBehaviour {

    @Override
    public void action() {
//      ACLMessage msg = receive();
//
//      if (msg != null) {
//        // Process the received message
//        try {
//          _labirinth = (Labirinth) msg.getContentObject();
//        } catch (UnreadableException ex) {
//          Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
//        }
//      } else {
//        block();
//      }
    }
  }

  // Searches for the exit if the labyrinth
  private class SearchExit extends SimpleBehaviour {

    @Override
    public void action() {
//      Cell currentPosition = _home;
//
//      // Run until the exit is found
//      while (!ExitFound()) {
//        Move nextMove = currentPosition.getNextPossibleMove();
//
//        // If there is a possible move ...
//        if (nextMove != null) {
//          // Set the move as tried
//          currentPosition.getNextPossibleMove().setWasTried(true);
//
//          // Keep the current position in the path
//          _path.push(currentPosition);
//
//          // Make a move
//          currentPosition.makeNextMove();
//        } // Do some backtracking
//        else {
//          // While the next move is a tried cell ...
//          while (_path.peek().getNextPossibleMove().getWasTried()) {
//            // Pop the cell from backtracking
//            Cell backCell = _path.pop();
//
//            // Make the backtrack move
//            currentPosition.moveTo(backCell);
//          }
//        }
//      }
    }

    @Override
    public boolean done() {
      // todo
      return true || ExitFound();
    }
  }

  // Informs that the exit of the labyrinth was found
  private class OfferExit extends SimpleBehaviour {
    // ...

    @Override
    public void action() {
//      String msgToSend = "EXITFOUND";
//      ACLMessage exitFound = new ACLMessage(ACLMessage.INFORM);
//      exitFound.addReceiver(new AID("referee", AID.ISLOCALNAME));
//      exitFound.setContent(msgToSend);
//      send(exitFound);
    }

    @Override
    public boolean done() {
      return true;
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  }

  // Listens to offers from other agents / the referee (yup)
  private class ListenToOffers extends CyclicBehaviour {

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
//      ACLMessage msg = receive();
//
//      if (msg != null) {
//        String msgExpected = "EXITFOUND";
//
//        // Process the received message
//        String msgReceived = msg.getContent();
//
//        // Check the received message
//        if (msgReceived.equals(msgExpected)) {
//          // Terminate agent
//        }
//      } else {
//        block();
//      }
    }
  }

}
