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

  Labirinth _labirinth;
  Stack<Cell> _path;        // J: acrescentei <Cell> para o programa saber que é uma stack de células
  Cell _home;
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
            
            initPos = _labirinth.getCell(homeX, homeY);
          } while (initPos == _labirinth.getExit() || initPos.IsWall());
          
          return initPos;
      }
      else return null;
  }

  @Override
  protected void setup() {
    // Printout a welcome message
    System.out.println(String.format("LOG %S: Player is starting!", getAID().getName()));

    //this._labirinth = labirinth;
    //this._home = home;
    //_path = new Stack<Cell>();
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
    sb.addSubBehaviour(new OfferExit());

    // Adds the SB and ListenToOffers behaviours to the PB
    pb.addSubBehaviour(sb);
    pb.addSubBehaviour(new ListenToOffers());

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
    return _path.peek().equals(_labirinth.getExit());
  }

  public Cell Move() {
    // try to move;
    return null;
  }

  // Waits for the referee to transmit the labyrinth object and processes it
  private class Initialize extends CyclicBehaviour {
      
      private static final long serialVersionUID = 1L;
      boolean finished;

      @Override
      public void action() {
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
                    msg.setContent("tungas");
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
                            System.out.println(String.format("LOG %s: Home = %s", getAID().getName(),_home.toString()));
                        }
                    } catch (UnreadableException ex) {
                            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        finished = true;
        } catch (FIPAException ex) {
            System.out.println(String.format("ERR %s: %s", getAID().getName(), ex.getMessage()));
        }
    }
}
      
      
//    @Override
//    public void action() {
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
//    }
//  }

  // Searches for the exit if the labyrinth
  private class SearchExit extends SimpleBehaviour {

    @Override
    public void action() {
//000
     if (labirinthReceived) {
        Cell currentPosition = _home;

        // Run until the exit is found
        do {
          Move nextMove = currentPosition.getNextPossibleMove();

          // If there is a possible move ...
          if (nextMove != null) {
            // Set the move as tried
            currentPosition.getNextPossibleMove().setWasTried(true);

            // Keep the current position in the path
            _path.push(currentPosition);

            // Make a move
            currentPosition.makeNextMove();
          } // Do some backtracking
          else {
            // While the next move is a tried cell ...
            while (_path.peek().getNextPossibleMove().getWasTried()) {
              // Pop the cell from backtracking
              Cell backCell = _path.pop();

              // Make the backtrack move
              currentPosition.moveTo(backCell);
            }
          }
          System.out.println(String.format("LOG %s: Moved to = %s", getAID().getName(),currentPosition.toString()));
        } while (!ExitFound());
        System.out.println(String.format("LOG %s: Exit found!", getAID().getName()));
//000
      }
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
